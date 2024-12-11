package com.shepeliev.ksdp.parsers

import com.shepeliev.ksdp.*

internal fun interface Parser<T> {
    fun parse(text: String, lineNumber: Int): T
}

@Suppress("UNCHECKED_CAST")
internal fun String.parseLine(lineNumber: Int, parseResult: MutableMap<Char, Any>) {
    require(this.isNotBlank()) { "Blank line: $lineNumber" }
    checkIt(this.contains('=')) { "No '=' found at line $lineNumber: $this" }

    fun checkNoDuplicate(fieldType: Char) {
        checkIt(!parseResult.containsKey(fieldType)) { "Duplicate '$fieldType' field at line $lineNumber." }
    }

    fun parseUnicField(fieldType: Char, parser: Parser<*>) {
        checkNoDuplicate(fieldType)
        parseResult[fieldType] = parser.parse(this, lineNumber) as Any
    }

    return when (val fieldType = this.first()) {
        'v' -> {
            parseUnicField(fieldType, VersionParser)
        }

        'o' -> {
            parseUnicField(fieldType, OriginParser)
        }

        's' -> {
            parseUnicField(fieldType, SessionNameParser)
        }

        'i' -> {
            parseUnicField(fieldType, InfoParser)
        }

        'u' -> {
            parseUnicField(fieldType, UriParser)
        }

        'e' -> {
            parseUnicField(fieldType, EmailParser)
        }

        'p' -> {
            parseUnicField(fieldType, PhoneParser)
        }

        'c' -> {
            parseUnicField(fieldType, ConnectionParser)
        }

        'b' -> {
            val bandwidthList = parseResult.getOrPut('b') { mutableListOf<Bandwidth>() } as MutableList<Bandwidth>
            bandwidthList += BandwidthParser.parse(this, lineNumber)
        }

        't' -> {
            val timeList =
                parseResult.getOrPut('t') { mutableListOf<TimeDescription>() } as MutableList<TimeDescription>
            timeList += TimeDescriptionParser.parse(this, lineNumber)
        }

        'r' -> {
            val timeList = parseResult['t'] as MutableList<TimeDescription>
            if (timeList.isEmpty()) throw SdpParseException("'r' field must be after 't' field at line $lineNumber.")
            val lastTimeDescription = timeList.last()
            val repeats = lastTimeDescription.repeats + RepeatParser.parse(this, lineNumber)
            timeList[timeList.lastIndex] = lastTimeDescription.copy(repeats = repeats)
        }

        'z' -> {
            parseResult['z'] = ZoneAdjustmentParser.parse(this, lineNumber)
        }

        'k' -> {
            parseUnicField(fieldType, KeyParser)
        }

        'a' -> {
            val attributeList = parseResult.getOrPut('a') { mutableListOf<Attribute>() } as MutableList<Attribute>
            attributeList += AttributeParser.parse(this, lineNumber)
        }

        else -> throw SdpParseException("Unknown field type at line $lineNumber: $this")
    }
}