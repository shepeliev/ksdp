package com.shepeliev.ksdp.parsers

import com.shepeliev.ksdp.Connection
import com.shepeliev.ksdp.SdpParseException

internal object ConnectionParser : Parser<Connection> {
    override fun parse(text: String, lineNumber: Int): Connection {
        val (type, value) = text.split("=", limit = 2)
        require(type == "c") { "Unexpected field type '$type' at line $lineNumber. Expected type: 'c'" }
        val (networkType, addressType, address) = value.split(" ")

        if (networkType.isEmpty()) throw SdpParseException("Invalid connection 'c' network type at line: $lineNumber: $text")
        if (addressType.isEmpty()) throw SdpParseException("Invalid connection 'c' address type at line: $lineNumber: $text")
        if (address.isEmpty()) throw SdpParseException("Invalid connection 'c' address at line: $lineNumber: $text")

        return Connection(address, addressType, networkType)
    }
}