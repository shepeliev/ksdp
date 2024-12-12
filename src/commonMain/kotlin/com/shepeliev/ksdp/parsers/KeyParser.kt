package com.shepeliev.ksdp.parsers

import com.shepeliev.ksdp.Key
import com.shepeliev.ksdp.SdpParseException
import com.shepeliev.ksdp.checkIt

internal object KeyParser : Parser<Key> {
    override fun parse(text: String, lineNumber: Int): Key {
        val (fieldType, fieldValue) = text.split("=")
        require(fieldType == "k") { "Unexpected field type '$fieldType' at line $lineNumber. Expected type: 'k'" }

        if (fieldValue == "prompt") return Key.Prompt
        if (!fieldValue.contains(":")) return Key.Unknown(fieldValue, null)

        val (method, key) = fieldValue.split(":")
        return when (method) {
            "clear" -> Key.Clear(key)
            "base64" -> Key.Base64(key)
            "uri" -> Key.Uri(key)
            else -> Key.Unknown(method, key)
        }
    }
}