package com.shepeliev.ksdp

import com.github.h0tk3y.betterParse.combinators.and
import com.github.h0tk3y.betterParse.combinators.map
import com.github.h0tk3y.betterParse.combinators.skip
import com.github.h0tk3y.betterParse.combinators.unaryMinus
import com.github.h0tk3y.betterParse.parser.Parser
import com.shepeliev.ksdp.grammar.*
import com.shepeliev.ksdp.grammar.eq
import com.shepeliev.ksdp.grammar.oneOrMoreAsText
import com.shepeliev.ksdp.grammar.v

data class SessionName(val value: String = " ") : Field(FieldType.Version) {
    override fun encode(): String = "${type.type}=$value"

    override fun toString(): String = encode()

    internal companion object Grammar : BaseGrammar<SessionName>() {
        // session-name-field =  %x73 "=" text
        private val protoVersion by -s and -eq and text map { SessionName(it) }
        override val rootParser: Parser<SessionName> = protoVersion
    }
}