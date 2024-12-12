package com.shepeliev.ksdp.parsers

import com.shepeliev.ksdp.SdpParseException

internal object VersionParser : Parser<Int> {
    override fun parse(text: String, lineNumber: Int): Int {
        val (fieldType, fieldValue) = text.split('=')
        require(fieldType == "v") { "Unexpected field type '$fieldType' at line $lineNumber. Expected type: 'v'" }

        return try {
            fieldValue.toInt()
        } catch (e: NumberFormatException) {
            throw SdpParseException("Invalid version number at line $lineNumber: $text")
        }
    }
}
