package com.shepeliev.ksdp.parsers

import com.shepeliev.ksdp.SdpParseException

internal class StringParser(private val fieldType: Char) : Parser<String> {
    override fun parse(text: String, lineNumber: Int): String {
        require(text.isNotBlank()) { "Blank line: $lineNumber" }
        require(text.first() == fieldType) { "Unexpected field type \"${text.first()}\" line $lineNumber: $text. Expected type: $fieldType" }
        val (_, value) = text.split('=')
        if (value.isEmpty()) throw SdpParseException("Empty field \"${fieldType}\" value at line $lineNumber.")
        return value
    }
}