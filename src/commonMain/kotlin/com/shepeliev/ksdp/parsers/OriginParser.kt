package com.shepeliev.ksdp.parsers

import com.shepeliev.ksdp.Origin
import com.shepeliev.ksdp.SdpParseException

internal object OriginParser : Parser<Origin> {
    override fun parse(text: String, lineNumber: Int): Origin {
        val (type, value) = text.split("=", limit = 2)
        require(type == "o") { "Unexpected field type '$type' at line $lineNumber. Expected type: 'o'" }
        val values = value.split(" ")
        if (values.size != 6) throw  SdpParseException("Invalid 'o' field value at line: $lineNumber: $text")

        val sessionId = try {
            values[1].toLong()
        } catch (e: NumberFormatException) {
            throw SdpParseException("Invalid session id at line: $lineNumber: $text")
        }

        val sessionVersion = try {
            values[2].toLong()
        } catch (e: NumberFormatException) {
            throw SdpParseException("Invalid session version at line: $lineNumber: $text")
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