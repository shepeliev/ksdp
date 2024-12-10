package com.shepeliev.ksdp

import com.github.h0tk3y.betterParse.combinators.map
import com.github.h0tk3y.betterParse.combinators.times
import com.github.h0tk3y.betterParse.combinators.unaryMinus
import com.github.h0tk3y.betterParse.parser.Parser
import com.shepeliev.ksdp.Sdp.Companion.FQDN
import com.shepeliev.ksdp.Sdp.Companion.ip4Address
import com.shepeliev.ksdp.Sdp.Companion.ip6Address
import com.shepeliev.ksdp.grammar.*
import com.shepeliev.ksdp.grammar.DIGIT
import com.shepeliev.ksdp.grammar.oneOrMoreAsText
import com.shepeliev.ksdp.grammar.token

data class Origin(
    val username: String,
    val sessionId: Long,
    val sessionVersion: Long,
    val networkType: String,
    val addressType: String,
    val address: String
) : Field(FieldType.Origin) {
    override fun encode(): String = "o=$username $sessionId $sessionVersion $networkType $addressType $address"

    companion object Grammar : BaseGrammar<Origin>() {
        private val username: Parser<String> by nonWsString
        private val sessId: Parser<String> by oneOrMoreAsText(DIGIT)
        private val sessVer: Parser<String> by oneOrMoreAsText(DIGIT)
        private val networkType: Parser<String> by token
        private val addressType: Parser<String> by token
        private val address: Parser<String> by token

        // unicast-address =     IP4-address / IP6-address / FQDN / extn-addr
        internal val unicastAddress by ip4Address orNext ip6Address orNext FQDN orNext extnAddr

        private val origin: Parser<Origin> by -o * -equal * username * -SP * sessId * -SP * sessVer * -SP * networkType * -SP * addressType * -SP * unicastAddress map { (username, sessionId, sessionVersion, networkType, addressType, address) ->
            Origin(username, sessionId.toLong(), sessionVersion.toLong(), networkType, addressType, address)
        }

        override val rootParser: Parser<Origin> by origin
    }
}