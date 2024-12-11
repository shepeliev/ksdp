package com.shepeliev.ksdp.parsers

import com.shepeliev.ksdp.Repeat
import com.shepeliev.ksdp.SdpParseException
import com.shepeliev.ksdp.checkIt
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

internal object RepeatParser : FieldParser<Repeat> {
    override fun parse(line: String, lineNumber: Int): Repeat {
        val (fieldType, value) = line.split("=")
        require(fieldType == "r") { "\"Unexpected field type '$fieldType' at line $lineNumber. Expected type: 'r'\"" }

        val values = value.split(" ")
        checkIt(values.size > 2) { "Invalid repeat field at line $lineNumber: $line" }

        val interval = parseTypedTime(values[0], lineNumber)
        val activeDuration = parseTypedTime(values[1], lineNumber)
        val offsets = values.drop(2).map { parseTypedTime(it, lineNumber) }

        return Repeat(interval, activeDuration, offsets)
    }

    private fun parseTypedTime(value: String, lineNumber: Int): Duration {
        return try {
            when {
                value.endsWith("d") -> value.dropLast(1).toLong().days
                value.endsWith("h") -> value.dropLast(1).toLong().hours
                value.endsWith("m") -> value.dropLast(1).toLong().minutes
                value.endsWith("s") -> value.dropLast(1).toLong().seconds
                else -> value.toLong().seconds
            }
        } catch (e: NumberFormatException) {
            throw SdpParseException("Invalid time description at line $lineNumber: $value")
        }
    }
}