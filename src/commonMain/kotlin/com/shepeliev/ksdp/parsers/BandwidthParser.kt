package com.shepeliev.ksdp.parsers

import com.shepeliev.ksdp.Bandwidth
import com.shepeliev.ksdp.SdpParseException

internal object BandwidthParser : FieldParser<Bandwidth> {
    override fun parse(line: String, lineNumber: Int): Bandwidth {
        val (fieldType, value) = line.split("=")
        require(fieldType == "b") { "Unexpected field type '$fieldType' at line $lineNumber. Expected type: 'b'" }

        val (bandwidthType, bandwidth) = value.split(":")
        val bandwidthValue = try {
            bandwidth.toInt()
        } catch (e: NumberFormatException) {
            throw SdpParseException("Invalid bandwidth value '$bandwidth' at line $lineNumber")
        }

        return Bandwidth(bandwidthType, bandwidthValue)
    }
}