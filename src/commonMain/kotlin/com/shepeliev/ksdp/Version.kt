package com.shepeliev.ksdp

import com.github.h0tk3y.betterParse.combinators.and
import com.github.h0tk3y.betterParse.combinators.map
import com.github.h0tk3y.betterParse.combinators.skip
import com.github.h0tk3y.betterParse.combinators.unaryMinus
import com.github.h0tk3y.betterParse.parser.Parser
import com.shepeliev.ksdp.grammar.*
import com.shepeliev.ksdp.grammar.equal
import com.shepeliev.ksdp.grammar.oneOrMoreAsText
import com.shepeliev.ksdp.grammar.v

data class Version(val value: Int = 0) : Field(FieldType.Version) {
    override fun encode(): String = "${type.type}=$value"

    override fun toString(): String = encode()

    internal companion object Grammar : BaseGrammar<Version>() {
        // proto-version =       %x76 "=" 1*DIGIT
        private val protoVersion by skip(v) and -equal and oneOrMoreAsText(DIGIT) map { Version(it.toInt()) }
        override val rootParser: Parser<Version> = protoVersion
    }
}