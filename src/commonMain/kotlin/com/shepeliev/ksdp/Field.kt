package com.shepeliev.ksdp

import com.github.h0tk3y.betterParse.grammar.tryParseToEnd
import com.github.h0tk3y.betterParse.parser.ParseException
import com.github.h0tk3y.betterParse.parser.toParsedOrThrow
import com.shepeliev.ksdp.grammar.BaseGrammar

/**
 * A Field represents a single line of information within a SDP session description.
 */
sealed class Field(val type: FieldType<*>) {
    abstract fun encode(): String

    companion object {
        fun parse(line: String, lineNumber: Int = 1): Field {
            require(line.isNotEmpty()) { "Line must not be empty" }
            val parseResult = when (val lineType = line.firstOrNull()) {
                FieldType.Version.type -> FieldType.Version.parser.tryParseToEnd(line)
                FieldType.Origin.type -> FieldType.Origin.parser.tryParseToEnd(line)
                else -> throw SdpParseException("Unknown field type \"$lineType=\" in line: $lineNumber")
            }

            return try {
                parseResult.toParsedOrThrow().value
            } catch (e: ParseException) {
                throw SdpParseException(e.message)
            }
        }
    }
}

sealed class FieldType<T>(val type: Char, internal val parser: BaseGrammar<T>) {
    data object Version : FieldType<com.shepeliev.ksdp.Version>('v', com.shepeliev.ksdp.Version.Grammar)
    data object Origin : FieldType<com.shepeliev.ksdp.Origin>('o', com.shepeliev.ksdp.Origin.Grammar)

    operator fun component1() = type
    operator fun component2() = parser
}