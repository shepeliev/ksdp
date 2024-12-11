package com.shepeliev.ksdp

import com.shepeliev.ksdp.parsers.parseLine

/**
 * A SessionDescription represents the data defined by the Session Description
 * Protocol (see IETF RFC 2327) and holds information about the originitor of a session,
 * the media types that aclient can support and the host and port on which the client will listen
 * for that media.
 *
 * The SessionDescription also holds timing information for the session (e.g. start, end,
 * repeat, time zone) and bandwidth supported for the session.
 *
 * Please refer to IETF RFC 2327 for a description of SDP.
 */
public data class SessionDescription(
    val origin: Origin,
    val sessionName: String,
    val version: Int = 0,
    val info: String? = null,
    val uri: String? = null,
    val email: String? = null,
) {

    override fun toString(): String = buildString {
        append("v=$version")
        append("\r\n")
        append("o=$origin")
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
        email?.let {
            append("e=$it")
            append("\r\n")
        }
    }
}

/**
 * Parses SDP string into [SessionDescription] object.
 */
public fun String.sessionDescription(): SessionDescription {
    val parseResults = mutableMapOf<String, Any>()

    this.trim().lines().forEachIndexed { index, line ->
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
        email = parseResults["e"] as String?,
    )
}