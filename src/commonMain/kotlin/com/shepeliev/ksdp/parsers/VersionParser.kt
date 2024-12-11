package com.shepeliev.ksdp.parsers

import com.shepeliev.ksdp.SdpParseException

internal object VersionParser : Parser<Int> {
    override fun parse(text: String, lineNumber: Int): Int {
        val (type, value) = text.split('=')
        require(type == "v") { "Unexpected field type '$type' at line $lineNumber. Expected type: 'v'" }

        return try {
            value.toInt()
        } catch (e: NumberFormatException) {
            throw SdpParseException("Invalid version number at line $lineNumber: $value")
        }
    }
}
