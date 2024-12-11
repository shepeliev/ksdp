package com.shepeliev.ksdp.parsers

import com.shepeliev.ksdp.SdpParseException
import com.shepeliev.ksdp.TimeDescription

internal fun interface FieldParser<T> {
    fun parse(line: String, lineNumber: Int): T
}

@Suppress("UNCHECKED_CAST")
internal fun String.parseLine(lineNumber: Int, parseResult: MutableMap<String, Any>) {
    require(this.isNotBlank()) { "Blank line: $lineNumber" }

    return when (val fieldType = this.first()) {
        'v' -> {
            if (parseResult.containsKey("v")) throw SdpParseException("Duplicate version (v) field at line $lineNumber.")
            parseResult["v"] = VersionParser.parse(this, lineNumber)
        }

        'o' -> {
            if (parseResult.containsKey("o")) throw SdpParseException("Duplicate origin (o) field at line $lineNumber.")
            parseResult["o"] = OriginParser.parse(this, lineNumber)
        }

        's' -> {
            if (parseResult.containsKey("s")) throw SdpParseException("Duplicate session name (s) field at line $lineNumber.")
            parseResult["s"] = SessionNameParser.parse(this, lineNumber)
        }

        'i' -> {
            if (parseResult.containsKey("i")) throw SdpParseException("Duplicate information (i) field at line $lineNumber.")
            parseResult["i"] = InfoParser.parse(this, lineNumber)
        }

        'u' -> {
            if (parseResult.containsKey("u")) throw SdpParseException("Duplicate URI (u) field at line $lineNumber.")
            parseResult["u"] = UriParser.parse(this, lineNumber)
        }

        'e' -> {
            if (parseResult.containsKey("e")) throw SdpParseException("Duplicate email (e) field at line $lineNumber.")
            parseResult["e"] = EmailParser.parse(this, lineNumber)
        }

        'p' -> {
            if (parseResult.containsKey("p")) throw SdpParseException("Duplicate phone (p) field at line $lineNumber.")
            parseResult["p"] = PhoneParser.parse(this, lineNumber)
        }

        'c' -> {
            if (parseResult.containsKey("c")) throw SdpParseException("Duplicate connection data (c) field at line $lineNumber.")
            parseResult["c"] = ConnectionParser.parse(this, lineNumber)
        }

        'b' -> {
            val bandwidthList = parseResult["b"] as MutableList<com.shepeliev.ksdp.Bandwidth>
            bandwidthList += BandwidthParser.parse(this, lineNumber)
        }

        't' -> {
            val timeList = parseResult["t"] as MutableList<TimeDescription>
            timeList += TimeDescriptionParser.parse(this, lineNumber)
        }

        'r' -> {
            val timeList = parseResult["t"] as MutableList<TimeDescription>
            if (timeList.isEmpty()) throw SdpParseException("Repeat field (r) must be after time field (t) at line $lineNumber.")
            val lastTimeDescription = timeList.last()
            val repeats = lastTimeDescription.repeats + RepeatParser.parse(this, lineNumber)
            timeList[timeList.lastIndex] = lastTimeDescription.copy(repeats = repeats)
        }

        else -> throw SdpParseException("Unknown field type at line $lineNumber: $fieldType")
    }
}