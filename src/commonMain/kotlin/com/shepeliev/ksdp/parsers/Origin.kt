package com.shepeliev.ksdp.parsers

import com.shepeliev.ksdp.Origin
import com.shepeliev.ksdp.SdpParseException

internal object Origin : FieldParser<Origin> {
    override fun parse(line: String, lineNumber: Int): Origin {
        val (type, value) = line.split("=", limit = 2)
        require(type == "o") { "Expected 'o' field, but got '$type'." }
        val values = value.split(" ")
        if (values.size != 6) throw  SdpParseException("Invalid 'o' field value at line: $lineNumber: $line")

        val sessionId = try {
            values[1].toLong()
        } catch (e: NumberFormatException) {
            throw SdpParseException("Invalid session id at line: $lineNumber: $line")
        }

        val sessionVersion = try {
            values[2].toLong()
        } catch (e: NumberFormatException) {
            throw SdpParseException("Invalid session version at line: $lineNumber: $line")
        }

        return Origin(
            username = values[0],
            sessionId = sessionId,
            sessionVersion = sessionVersion,
            networkType = values[3],
            addressType = values[4],
            address = values[5]
        )
    }
}