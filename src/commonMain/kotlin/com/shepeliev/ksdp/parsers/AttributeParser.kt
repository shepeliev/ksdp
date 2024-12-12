package com.shepeliev.ksdp.parsers

import com.shepeliev.ksdp.Attribute

public object AttributeParser : Parser<Attribute> {
    override fun parse(text: String, lineNumber: Int): Attribute {
        val (fieldType, fieldValue) = text.split("=", limit = 2)
        require(fieldType == "a") { "Unexpected field type '$fieldType' at line $lineNumber. Expected type: 'a'" }

        return if (fieldValue.contains(":")) {
            val (name, value) = fieldValue.split(":")
            Attribute.NameValue(name, value)
        } else {
            Attribute.Identity(fieldValue)
        }
    }
}
