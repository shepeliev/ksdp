package com.shepeliev.ksdp

import com.github.h0tk3y.betterParse.combinators.and
import com.github.h0tk3y.betterParse.combinators.unaryMinus
import com.github.h0tk3y.betterParse.parser.Parser
import com.shepeliev.ksdp.grammar.*

public class SessionName internal constructor(
    private val line: String,
    private val lineNumber: Int = 1,
) : Field(Type.SESSION_NAME) {

    val value: String by lazy { parse(line, lineNumber) }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SessionName

        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString(): String = line

    internal companion object Grammar : BaseGrammar<String>() {
        // session-name-field =  %x73 "=" text
        private val protoVersion by -s and -eq and text
        override val rootParser: Parser<String> = protoVersion
    }
}

public fun SessionName(text: String = " "): SessionName = SessionName(line = "${Field.Type.SESSION_NAME.type}=$text")