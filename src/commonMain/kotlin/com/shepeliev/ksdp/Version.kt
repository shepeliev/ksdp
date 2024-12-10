package com.shepeliev.ksdp

import com.github.h0tk3y.betterParse.combinators.and
import com.github.h0tk3y.betterParse.combinators.map
import com.github.h0tk3y.betterParse.combinators.unaryMinus
import com.github.h0tk3y.betterParse.parser.Parser
import com.shepeliev.ksdp.grammar.*
import com.shepeliev.ksdp.grammar.oneOrMoreAsText

//data class Version(val value: Int = 0) : Field(FieldType.Version) {
//    override fun encode(): String = "${type.type}=$value"
//
//    override fun toString(): String = encode()
//
//    internal companion object Grammar : BaseGrammar<Version>() {
//        // proto-version =       %x76 "=" 1*DIGIT
//        private val protoVersion by -v and -eq and oneOrMoreAsText(DIGIT) map { Version(it.toInt()) }
//        override val rootParser: Parser<Version> = protoVersion
//    }
//}

class Version internal constructor(private val line: String, private val lineNumber: Int = 1) : Field(Type.VERSION) {
    val value by lazy { parse(line, lineNumber) }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Version

        if (value != other.value) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = value
        result = 31 * result + type.hashCode()
        return result
    }

    override fun toString(): String = line

    internal companion object : BaseGrammar<Int>() {
        // proto-version =       %x76 "=" 1*DIGIT
        private val protoVersion by -v and -eq and oneOrMoreAsText(DIGIT) map { it.toInt() }
        override val rootParser: Parser<Int> = protoVersion
    }
}

fun Version(value: Int): Version = Version("${Field.Type.VERSION.type}=$value")