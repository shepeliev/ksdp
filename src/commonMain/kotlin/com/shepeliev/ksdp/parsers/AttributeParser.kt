package com.shepeliev.ksdp.parsers

import com.shepeliev.ksdp.Attribute

public object AttributeParser : Parser<Attribute> {
    override fun parse(text: String, lineNumber: Int): Attribute {
        val (fieldType, fieldValue) = text.split("=", limit = 2)

        return when {
            fieldType != "a" && fieldValue.contains(":") -> {
                val name = fieldValue.substringBefore(":")
                Attribute.Invalid(name, text)
            }

            fieldType != "a" -> {
                Attribute.Invalid(fieldValue, text)
            }

            fieldValue.contains(":") -> {
                val (name, value) = fieldValue.split(":", limit = 2)
                Attribute.NameValue(name, value)
            }

            else -> Attribute.Identity(fieldValue)
        }
    }
}
