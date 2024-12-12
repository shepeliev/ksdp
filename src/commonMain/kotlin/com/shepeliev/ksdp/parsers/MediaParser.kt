package com.shepeliev.ksdp.parsers

import com.shepeliev.ksdp.Media
import com.shepeliev.ksdp.SdpParseException

internal object MediaParser : Parser<Media> {
    override fun parse(text: String, lineNumber: Int): Media {
        val (fieldType, fieldValue) = text.split("=", limit = 2)
        require(fieldType == "m") { "Unexpected field type '$fieldType' at line $lineNumber. Expected type: 'm'" }
        val values = fieldValue.split(" ")
        if (values.size < 4) throw SdpParseException("Invalid media 'm' field at line: $lineNumber: $text")

        val media = values[0]
        val port = parsePort(text, lineNumber, values[1])
        val protocol = values[2]
        val formats = values.drop(3)

        return Media(media, port.first, protocol, formats.toMutableList(), port.second)
    }

    private fun parsePort(lineText: String, lineNumber: Int, portText: String): Pair<Int, Int> {
        return if (portText.contains("/")) {
            val (port, numberOfPorts) = portText.split("/")
            val portNumber =
                port.toIntOrNull() ?: throw SdpParseException("Invalid media 'm' port at line: $lineNumber: $lineText")
            val numberOfPortsNumber = numberOfPorts.toIntOrNull()
                ?: throw SdpParseException("Invalid media 'm' port at line: $lineNumber: $lineText")
            portNumber to numberOfPortsNumber
        } else {
            val portNumber = portText.toIntOrNull()
                ?: throw SdpParseException("Invalid media 'm' port at line: $lineNumber: $lineText")
            portNumber to 1
        }
    }
}
