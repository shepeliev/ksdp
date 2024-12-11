package com.shepeliev.ksdp.parsers

import com.shepeliev.ksdp.Key
import com.shepeliev.ksdp.SdpParseException
import com.shepeliev.ksdp.checkIt

internal object KeyParser : Parser<Key> {
    override fun parse(text: String, lineNumber: Int): Key {
        val (fieldType, value) = text.split("=")
        require(fieldType == "k") { "Unexpected field type '$fieldType' at line $lineNumber. Expected type: 'k'" }

        checkIt(value == "prompt" || value.contains(":")) {
            "Invalid key value at line $lineNumber: $text. Expected format: 'method:key' or 'prompt'"
        }

        if (value == "prompt") return Key.Prompt

        val (method, key) = value.split(":")

        return when (method) {
            "clear" -> Key.Clear(key)
            "base64" -> Key.Base64(key)
            "uri" -> Key.Uri(key)
            else -> throw SdpParseException("Unknown key method '$method' at line $lineNumber: $text")
        }
    }
}