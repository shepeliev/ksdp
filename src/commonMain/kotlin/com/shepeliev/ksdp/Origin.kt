package com.shepeliev.ksdp

import com.github.h0tk3y.betterParse.combinators.map
import com.github.h0tk3y.betterParse.combinators.times
import com.github.h0tk3y.betterParse.combinators.unaryMinus
import com.github.h0tk3y.betterParse.parser.Parser
import com.shepeliev.ksdp.Sdp.Companion.FQDN
import com.shepeliev.ksdp.Sdp.Companion.ip4Address
import com.shepeliev.ksdp.Sdp.Companion.ip6Address
import com.shepeliev.ksdp.grammar.*
import com.shepeliev.ksdp.grammar.oneOrMoreAsText

data class Origin internal constructor(private val line: String, private val lineNumber: Int = 1) :
    Field(Type.ORIGIN) {
    private val _origin: _Origin by lazy { parse(line, lineNumber) }

    val username: String by _origin::username
    val sessionId: Long by _origin::sessionId
    val sessionVersion: Long by _origin::sessionVersion
    val networkType: String by _origin::networkType
    val addressType: String by _origin::addressType
    val address: String by _origin::address

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Origin

        return _origin == other._origin
    }

    override fun hashCode(): Int {
        return _origin.hashCode()
    }

    override fun toString(): String = line

    private companion object Grammar : BaseGrammar<_Origin>() {
        private val username: Parser<String> by nonWsString
        private val sessId: Parser<String> by oneOrMoreAsText(DIGIT)
        private val sessVer: Parser<String> by oneOrMoreAsText(DIGIT)
        private val networkType: Parser<String> by token
        private val addressType: Parser<String> by token

        // unicast-address =     IP4-address / IP6-address / FQDN / extn-addr
        private val unicastAddress by ip4Address orNext ip6Address orNext FQDN orNext extnAddr

        private val origin: Parser<_Origin> by -o * -eq * username * -SP * sessId * -SP * sessVer * -SP * networkType * -SP * addressType * -SP * unicastAddress map { (username, sessionId, sessionVersion, networkType, addressType, address) ->
            _Origin(username, sessionId.toLong(), sessionVersion.toLong(), networkType, addressType, address)
        }

        override val rootParser: Parser<_Origin> by origin
    }


}

private data class _Origin(
    val username: String,
    val sessionId: Long,
    val sessionVersion: Long,
    val networkType: String,
    val addressType: String,
    val address: String,
)

fun Origin(
    username: String,
    sessionId: Long,
    sessionVersion: Long,
    networkType: String,
    addressType: String,
    address: String,
): Origin = Origin("o=$username $sessionId $sessionVersion $networkType $addressType $address")