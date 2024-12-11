package com.shepeliev.ksdp.parsers

import com.shepeliev.ksdp.Repeat
import com.shepeliev.ksdp.checkIt

internal object RepeatParser : Parser<Repeat> {
    override fun parse(text: String, lineNumber: Int): Repeat {
        val (fieldType, value) = text.split("=")
        require(fieldType == "r") { "\"Unexpected field type '$fieldType' at line $lineNumber. Expected type: 'r'\"" }

        val values = value.split(" ")
        checkIt(values.size > 2) { "Invalid repeat field at line $lineNumber: $text" }

        val interval = TypedTimeParser.parse(values[0], lineNumber)
        val activeDuration = TypedTimeParser.parse(values[1], lineNumber)
        val offsets = values.drop(2).map { TypedTimeParser.parse(it, lineNumber) }

        return Repeat(interval, activeDuration, offsets)
    }
}

