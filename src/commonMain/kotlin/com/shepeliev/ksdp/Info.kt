package com.shepeliev.ksdp

import com.github.h0tk3y.betterParse.combinators.and
import com.github.h0tk3y.betterParse.combinators.unaryMinus
import com.github.h0tk3y.betterParse.parser.Parser
import com.shepeliev.ksdp.grammar.*

public class Info internal constructor(
    private val line: String,
    private val lineNumber: Int = 1,
) : Field(Type.SESSION_NAME) {

    public val value: String by lazy { parse(line, lineNumber) }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Info

        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString(): String = line

    internal companion object Grammar : BaseGrammar<String>() {
        // information-field =  "i=" text
        private val protoVersion by -i and -eq and text
        override val rootParser: Parser<String> = protoVersion
    }
}

public fun Info(text: String): Info {
    require(text.isNotEmpty()) { "Info text must not be empty." }
    return Info(line = "${Field.Type.INFO.type}=$text")
}