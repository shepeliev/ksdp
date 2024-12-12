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
    var origin: Origin,
    var sessionName: String,
    var version: Int = 0,
    var time: MutableList<TimeDescription> = mutableListOf(TimeDescription(Time())),
    var info: String? = null,
    var uri: String? = null,
    var email: String? = null,
    var phone: String? = null,
    var connection: Connection? = null,
    var bandwidth: MutableList<Bandwidth> = mutableListOf(),
    val zoneAdjustments: MutableList<ZoneAdjustment> = mutableListOf(),
    var key: Key? = null,
    var attributes: MutableList<Attribute> = mutableListOf(),
    var mediaDescriptions: MutableList<MediaDescription> = mutableListOf(),
)

/**
 * Returns a list of encoded lines of the SDP.
 */
public val SessionDescription.lines: List<String>
    get() = buildList {
        add("v=$version")
        add(origin.line)
        add("s=$sessionName")
        info?.let { add("i=$it") }
        uri?.let { add("u=$it") }
        email?.let { add("e=$it") }
        phone?.let { add("p=$it") }
        connection?.let { add(it.line) }
        bandwidth.forEach { add(it.line) }
        time.forEach { addAll(it.lines) }
        add("z=${zoneAdjustments.joinToString(" ")}")
        key?.let { add(it.line) }
        attributes.forEach { add(it.line) }
        mediaDescriptions.forEach { addAll(it.lines) }
    }

/**
 * Returns a string representation of the SDP.
 */
public fun SessionDescription.sdp(lineSeparator: String = "\r\n"): String {
    return lines.joinToString(lineSeparator, postfix = lineSeparator)
}

/**
 * Parses SDP string into [SessionDescription] object.
 */
@Suppress("UNCHECKED_CAST")
@Throws(SdpParseException::class)
public fun String.parseSdp(): SessionDescription {
    val parseResults = mutableMapOf<Char, Any>()

    this.trim().lines().forEachIndexed { index, line ->
        line.parseLine(index + 1, parseResults)
    }

    when {
        parseResults['v'] == null -> throw SdpParseException("SDP malformed: 'v' field is required.")
        parseResults['o'] == null -> throw SdpParseException("SDP malformed: 'o' field is required.")
        parseResults['s'] == null -> throw SdpParseException("SDP malformed: 's' field is required.")
        parseResults['t'] == null -> throw SdpParseException("SDP malformed: at least one 't' field is required.")
    }

    return SessionDescription(
        version = parseResults['v'] as Int,
        origin = parseResults['o'] as Origin,
        sessionName = parseResults['s'] as String,
        info = parseResults['i'] as String?,
        uri = parseResults['u'] as String?,
        email = parseResults['e'] as String?,
        phone = parseResults['p'] as String?,
        time = parseResults['t'] as MutableList<TimeDescription>,
        connection = parseResults['c'] as Connection?,
        bandwidth = parseResults['b'] as MutableList<Bandwidth>? ?: mutableListOf(),
        zoneAdjustments = parseResults['z'] as MutableList<ZoneAdjustment>? ?: mutableListOf(),
        key = parseResults['k'] as Key?,
        attributes = parseResults['a'] as MutableList<Attribute>? ?: mutableListOf(),
        mediaDescriptions = parseResults['m'] as MutableList<MediaDescription>? ?: mutableListOf(),
    )
}
