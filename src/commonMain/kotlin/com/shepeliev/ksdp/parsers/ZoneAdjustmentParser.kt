package com.shepeliev.ksdp.parsers

import com.shepeliev.ksdp.SdpParseException
import com.shepeliev.ksdp.ZoneAdjustment
import com.shepeliev.ksdp.checkIt
import com.shepeliev.ksdp.utils.ntpTimeToInstant

internal object ZoneAdjustmentParser : Parser<List<ZoneAdjustment>> {
    override fun parse(text: String, lineNumber: Int): List<ZoneAdjustment> {
        val (fieldType, fieldValue) = text.split("=")
        require(fieldType == "z") { "\"Unexpected field type '$fieldType' at line $lineNumber. Expected type: 'z'\"" }

        val values = fieldValue.split(" ")
        checkIt(values.size % 2 == 0) { "Invalid zone adjustment field at line $lineNumber: $text" }

        return values.windowed(size = 2, step = 2) { (t, o) ->
            val time = try {
                t.toLong().ntpTimeToInstant()
            } catch (e: NumberFormatException) {
                throw SdpParseException("Invalid time value '$t' at line $lineNumber: $text")
            }
            val offset = TypedTimeParser.parse(o, lineNumber)
            ZoneAdjustment(time, offset)
        }
    }
}

