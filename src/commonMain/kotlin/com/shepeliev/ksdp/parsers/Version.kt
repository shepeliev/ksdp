package com.shepeliev.ksdp.parsers

import com.shepeliev.ksdp.SdpParseException

internal object Version : FieldParser<Int> {
    override fun parse(line: String, lineNumber: Int): Int {
        val (prefix, value) = line.split('=')
        require(prefix == "v") { "Invalid version field at line $lineNumber: $line" }

        return try {
            value.toInt()
        } catch (e: NumberFormatException) {
            throw SdpParseException("Invalid version number at line $lineNumber: $value")
        }
    }
}
