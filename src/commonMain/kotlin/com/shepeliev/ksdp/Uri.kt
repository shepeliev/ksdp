package com.shepeliev.ksdp

import com.github.h0tk3y.betterParse.combinators.*
import com.github.h0tk3y.betterParse.parser.Parser
import com.shepeliev.ksdp.grammar.*

public class Uri internal constructor(
    private val line: String,
    private val lineNumber: Int = 1,
) : Field(Type.URI) {

    private val uri: _Uri by lazy { _Uri.parse(line, lineNumber) }

    public val value: String by lazy { "$uri" }
    public val scheme by uri::scheme
    public val host by lazy { uri.hierPart.authority?.host }
    public val port by lazy { uri.hierPart.authority?.port }
    public val userInfo by lazy { uri.hierPart.authority?.userInfo }
    public val path by lazy { uri.hierPart.path }
    public val query by uri::query
    public val fragment by uri::fragment

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Uri

        return uri == other.uri
    }
    override fun hashCode(): Int {
        return uri.hashCode()
    }

    override fun toString(): String = line

    internal companion object Grammar : BaseGrammar<String>() {
        // uri-field =  "u=" uri
        private val uri by -i and -eq and text
        override val rootParser: Parser<String> = uri
    }
}

public fun Uri(uri: String): Uri {
    require(uri.isNotEmpty()) { "URI must not be empty." }
    return Uri(line = "${Field.Type.URI.type}=$uri")
}

private data class _Uri(
    val scheme: String,
    val hierPart: HierPart,
    val query: String?,
    val fragment: String?,
) {

    override fun toString(): String {
        val query = query?.let { "?$it" } ?: ""
        val fragment = fragment?.let { "#$it" } ?: ""
        return "$scheme:$hierPart$query$fragment"
    }

    companion object : BaseGrammar<_Uri>() {
        // ==================== URI ABNF ====================
        // sub-delims    = "!" / "$" / "&" / "'" / "(" / ")"
        //                 / "*" / "+" / "," / ";" / "="
        private val subDelims: Parser<String> by exclamation or dollar or ampersand or singleQuote or leftParenthesis or rightParenthesis or asterisk or plus or comma or semicolon or eq

        // gen-delims    = ":" / "/" / "?" / "#" / "[" / "]" / "@"
        private val genDelims: Parser<String> by colon or slash or question or hash or leftSquareBracket or rightSquareBracket or at

        // reserved      = gen-delims / sub-delims
        private val reserved: Parser<String> by genDelims or subDelims

        // unreserved    = ALPHA / DIGIT / "-" / "." / "_" / "~"
        private val unreserved: Parser<String> by ALPHA or DIGIT or minus or dot or underscore or tilde

        // pct-encoded   = "%" HEXDIG HEXDIG
        private val pctEncoded: Parser<String> by percent * HEXDIG * HEXDIG map { it.text3 }

        // pchar         = unreserved / pct-encoded / sub-delims / ":" / "@"
        private val pchar: Parser<String> by unreserved or pctEncoded or subDelims or colon or at

        // query         = *( pchar / "/" / "?" )
        private val query: Parser<String> by zeroOrMore(pchar or slash or question) map { it.text }

        // fragment      = *( pchar / "/" / "?" )
        private val fragment: Parser<String> by zeroOrMore(pchar or slash or question) map { it.text }

        // reg-name      = *( unreserved / pct-encoded / sub-delims )
        private val regName: Parser<String> by zeroOrMore(unreserved or pctEncoded or subDelims) map { it.text }

        // segment-nz-nc = 1*( unreserved / pct-encoded / sub-delims / "@" )
        //                 ; non-zero-length segment without any colon ":"
        private val segmentNzNc: Parser<String> by 1..Int.MAX_VALUE timesAsText (unreserved or pctEncoded or subDelims or at)

        // segment-nz    = 1*pchar
        private val segmentNz: Parser<String> by 1..Int.MAX_VALUE timesAsText pchar

        // segment       = *pchar
        private val segment: Parser<String> by zeroOrMore(pchar) map { it.text }

        // path-empty    = 0<pchar>
        private val pathEmpty: Parser<String> by 0 timesAsText pchar

        // path-rootless = segment-nz *( "/" segment )
        private val pathRootless: Parser<String> by segmentNz * (0..Int.MAX_VALUE timesAsText (slash * segment map { it.text2} )) map { it.text2 }

        // path-noscheme = segment-nz-nc *( "/" segment )
        private val pathNoScheme: Parser<String> by segmentNzNc * (0..Int.MAX_VALUE timesAsText (slash * segment map { it.text2} )) map { it.text2 }

        // path-absolute = "/" [ segment-nz *( "/" segment ) ]
        private val optionalPathRootless: Parser<String> by optional(pathRootless) map { it ?: "" }
        private val pathAbsolute: Parser<String> by slash * optionalPathRootless map { it.text2 }

        // path-abempty  = *( "/" segment )
        private val pathAbEmpty: Parser<String> by zeroOrMoreAsText(slash * segment map { it.text2 })

        // path          = path-abempty    ; begins with "/" or is empty
        //                 / path-absolute   ; begins with "/" but not "//"
        //                 / path-noscheme   ; begins with a non-colon segment
        //                 / path-rootless   ; begins with a segment
        //                 / path-empty      ; zero characters
        private val path: Parser<String> by pathAbEmpty or pathAbsolute or pathNoScheme or pathRootless or pathEmpty

        // dec-octet     = DIGIT                 ; 0-9
        //                 / %x31-39 DIGIT         ; 10-99
        //                 / "1" 2DIGIT            ; 100-199
        //                 / "2" %x30-34 DIGIT     ; 200-249
        //                 / "25" %x30-35          ; 250-255
        private val decOctet: Parser<String> by decimalUchar
        // IPv4address   = dec-octet "." dec-octet "." dec-octet "." dec-octet
        private val IPv4Address: Parser<String> by decOctet * dot * decOctet * dot * decOctet * dot * decOctet map { it.text7 }

        // h16           = 1*4HEXDIG
        internal val h16: Parser<String> by 1..4 timesAsText HEXDIG

        // ls32          = ( h16 ":" h16 ) / IPv4address
        internal val ls32: Parser<String> by h16 * colon * h16 map { it.text3 } or IPv4Address

        // scheme        = ALPHA *( ALPHA / DIGIT / "+" / "-" / "." )
        internal val schema: Parser<String> by ALPHA and zeroOrMore(ALPHA or DIGIT or plus or minus or dot) map { (a, b) -> a + b.text }

        // userinfo      = *( unreserved / pct-encoded / sub-delims / ":" )
        internal val userInfo: Parser<String> by zeroOrMore(unreserved or pctEncoded or subDelims or colon) map { it.text }

        //         IPv6address    =  hexpart [ ":" IPv4address ]
        internal val IPv6Address: Parser<String> by hexpart //* optional(colon * IPv4Address) map { it.text2 }

        // IPvFuture     = "v" 1*HEXDIG "." 1*( unreserved / sub-delims / ":" )
        internal val IPvFuture: Parser<String> by v * (1..Int.MAX_VALUE timesAsText HEXDIG) * dot * (1..Int.MAX_VALUE timesAsText (unreserved or subDelims or colon)) map { it.text4 }

        // IP-literal    = "[" ( IPv6address / IPvFuture  ) "]"
        internal val IPLiteral: Parser<String> by leftSquareBracket * (IPv6Address or IPvFuture) * rightSquareBracket map { it.text3 }

        // host          = IP-literal / IPv4address / reg-name
        internal val host: Parser<String> by IPLiteral or IPv4Address or regName

        // port          = *DIGIT
        internal val uriPort: Parser<String> by zeroOrMore(DIGIT) map { it.text }

        // authority     = [ userinfo "@" ] host [ ":" port ]
        private val optionalUserInfo: Parser<String> by optional(userInfo * -at) map { it ?: "" }
        private val optionalPort: Parser<Int?> by optional(-colon * uriPort) map { it?.toInt() }
        internal val authority: Parser<Authority> by optionalUserInfo * host * optionalPort map { (userInfo, host, port) -> Authority(userInfo, host, port) }

        // hier-part     = "//" authority path-abempty
        //                 / path-absolute
        //                 / path-rootless
        //                 / path-empty
        private val doubleSlash by 2 timesAsText slash
        internal val hierPart: Parser<HierPart> by
        (-doubleSlash * authority * pathAbEmpty map { (authority, path) -> HierPart(path, authority) }) or
                (pathAbsolute or pathRootless or pathEmpty map { HierPart(it)})

        // URI           = scheme ":" hier-part [ "?" query ] [ "#" fragment ]
        private val optionalQuestionAndQuery: Parser<String?> by optional(-question * query)
        private val optionalHashAndFragment: Parser<String?> by optional(-hash * fragment)

        private val uri: Parser<_Uri> by schema * -colon * hierPart * optionalQuestionAndQuery * optionalHashAndFragment map {
            (schema, hierPart, query, fragment) -> _Uri(schema, hierPart, query, fragment)
        }

        override val rootParser: Parser<_Uri> by -u * -eq * uri

        data class Authority(val userInfo: String?, val host: String, val port: Int?) {
            override fun toString(): String {
                val userInfo = userInfo?.let { "$it@" } ?: ""
                val port = port?.let { ":$it" } ?: ""
                return "$userInfo$host$port"
            }
        }

        data class HierPart(val path: String, val authority: Authority? = null) {
            override fun toString(): String {
                val authority = authority?.let { "//$it" } ?: ""
                return "$authority$path"
            }
        }
    }
}