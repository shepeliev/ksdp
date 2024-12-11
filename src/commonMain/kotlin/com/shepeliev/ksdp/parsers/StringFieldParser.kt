package com.shepeliev.ksdp.parsers

import com.shepeliev.ksdp.SdpParseException

internal class StringFieldParser(private val fieldType: Char) : FieldParser<String> {
    override fun parse(line: String, lineNumber: Int): String {
        require(line.isNotBlank()) { "Blank line: $lineNumber" }
        require(line.first() == fieldType) { "Unexpected field type \"${line.first()}\" line $lineNumber: $line. Expected type: $fieldType" }
        val (_, value) = line.split('=')
        if (value.isEmpty()) throw SdpParseException("Empty field \"${fieldType}\" value at line $lineNumber.")
        return value
    }
}