package com.shepeliev.ksdp

import com.shepeliev.ksdp.parsers.parseLine

public data class SessionDescription(
    val origin: Origin,
    val sessionName: String,
    val version: Int = 0,
    val info: String? = null,
    val uri: String? = null,
) {
    override fun toString(): String = buildString {
        append("v=$version")
        append("\r\n")
        append(origin)
        append("\r\n")
        append("s=$sessionName")
        append("\r\n")
        info?.let {
            append("i=$it")
            append("\r\n")
        }
        uri?.let {
            append("u=$it")
            append("\r\n")
        }
    }
}

public fun String.sessionDescription(): SessionDescription {
    val parseResults = mutableMapOf<String, Any>()

    trim().lines().forEachIndexed { index, line ->
        line.parseLine(index + 1, parseResults)
    }

    when {
        parseResults["v"] == null -> throw SdpParseException("SDP malformed: \"v\" field is required.")
        parseResults["o"] == null -> throw SdpParseException("SDP malformed: \"o\" field is required.")
        parseResults["s"] == null -> throw SdpParseException("SDP malformed: \"s\" field is required.")
    }

    return SessionDescription(
        version = parseResults["v"] as Int,
        origin = parseResults["o"] as Origin,
        sessionName = parseResults["s"] as String,
        info = parseResults["i"] as String?,
        uri = parseResults["u"] as String?,
    )
}