package com.shepeliev.ksdp.parsers

import com.shepeliev.ksdp.SdpParseException
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

internal object TypedTimeParser : Parser<Duration> {
    override fun parse(text: String, lineNumber: Int): Duration {
        return try {
            when {
                text.endsWith("d") -> text.dropLast(1).toLong().days
                text.endsWith("h") -> text.dropLast(1).toLong().hours
                text.endsWith("m") -> text.dropLast(1).toLong().minutes
                text.endsWith("s") -> text.dropLast(1).toLong().seconds
                else -> text.toLong().seconds
            }
        } catch (e: NumberFormatException) {
            throw SdpParseException("Invalid typed time description at line $lineNumber: $text")
        }
    }
}