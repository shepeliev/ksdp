package com.shepeliev.ksdp.parsers

import com.shepeliev.ksdp.Connection
import com.shepeliev.ksdp.SdpParseException
import com.shepeliev.ksdp.checkIt

internal object ConnectionParser : Parser<Connection> {
    override fun parse(text: String, lineNumber: Int): Connection {
        val (fieldType, fieldValue) = text.split("=", limit = 2)
        require(fieldType == "c") { "Unexpected field type '$fieldType' at line $lineNumber. Expected type: 'c'" }

        val values = fieldValue.split(" ")
        checkIt(values.size == 3) { "Invalid connection 'c' field at line: $lineNumber: $text" }
        val (networkType, addressType, address) = values

        if (networkType.isEmpty()) throw SdpParseException("Invalid connection 'c' network type at line: $lineNumber: $text")
        if (addressType.isEmpty()) throw SdpParseException("Invalid connection 'c' address type at line: $lineNumber: $text")
        if (address.isEmpty()) throw SdpParseException("Invalid connection 'c' address at line: $lineNumber: $text")

        return Connection(address, addressType, networkType)
    }
}
