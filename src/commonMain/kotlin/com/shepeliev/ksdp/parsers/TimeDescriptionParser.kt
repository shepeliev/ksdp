package com.shepeliev.ksdp.parsers

import com.shepeliev.ksdp.SdpParseException
import com.shepeliev.ksdp.Time
import com.shepeliev.ksdp.TimeDescription
import com.shepeliev.ksdp.utils.ntpTimeToInstant

internal object TimeDescriptionParser : Parser<TimeDescription> {
    override fun parse(text: String, lineNumber: Int): TimeDescription {
        val (fieldType, value) = text.split("=")
        require(fieldType == "t") { "\"Unexpected field type '$fieldType' at line $lineNumber. Expected type: 't'\"" }
        val (start, stop) = value.split(" ")
            .map {
                try {
                    it.toLong()
                } catch (e: NumberFormatException) {
                    throw SdpParseException("Invalid time description at line $lineNumber: $value")
                }
            }
            .map { it.ntpTimeToInstant() }

        return TimeDescription(Time(start, stop))
    }
}