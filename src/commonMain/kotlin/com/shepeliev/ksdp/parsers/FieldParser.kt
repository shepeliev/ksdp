package com.shepeliev.ksdp.parsers

import com.shepeliev.ksdp.SdpParseException

internal fun interface FieldParser<T> {
    fun parse(line: String, lineNumber: Int): T
}

internal fun String.parseLine(lineNumber: Int, parseResult: MutableMap<String, Any>) {
    require(this.isNotBlank()) { "Blank line: $lineNumber" }

    return when (val fieldType = this.first()) {
        'v' -> {
            if (parseResult.containsKey("v")) throw SdpParseException("Duplicate version (v) field at line $lineNumber.")
            parseResult["v"] = Version.parse(this, lineNumber)
        }

        'o' -> {
            if (parseResult.containsKey("o")) throw SdpParseException("Duplicate origin (o) field at line $lineNumber.")
            parseResult["o"] = Origin.parse(this, lineNumber)
        }

        's' -> {
            if (parseResult.containsKey("s")) throw SdpParseException("Duplicate session name (s) field at line $lineNumber.")
            parseResult["s"] = SessionName.parse(this, lineNumber)
        }

        'i' -> {
            if (parseResult.containsKey("i")) throw SdpParseException("Duplicate information (i) field at line $lineNumber.")
            parseResult["i"] = Info.parse(this, lineNumber)
        }

        'u' -> {
            if (parseResult.containsKey("u")) throw SdpParseException("Duplicate URI (u) field at line $lineNumber.")
            parseResult["u"] = Uri.parse(this, lineNumber)
        }

        'e' -> {
            if (parseResult.containsKey("e")) throw SdpParseException("Duplicate email (e) field at line $lineNumber.")
            parseResult["e"] = Email.parse(this, lineNumber)
        }

        else -> throw SdpParseException("Unknown field type at line $lineNumber: $fieldType")
    }
}