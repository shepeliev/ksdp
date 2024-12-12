package com.shepeliev.ksdp.parsers

import com.shepeliev.ksdp.Bandwidth
import com.shepeliev.ksdp.SdpParseException

internal object BandwidthParser : Parser<Bandwidth> {
    override fun parse(text: String, lineNumber: Int): Bandwidth {
        val (fieldType, fieldValue) = text.split("=")
        require(fieldType == "b") { "Unexpected field type '$fieldType' at line $lineNumber. Expected type: 'b'" }

        val (bandwidthType, bandwidth) = fieldValue.split(":")
        val bandwidthValue = try {
            bandwidth.toInt()
        } catch (e: NumberFormatException) {
            throw SdpParseException("Invalid bandwidth value '$bandwidth' at line $lineNumber: $text")
        }

        return Bandwidth(bandwidthType, bandwidthValue)
    }
}