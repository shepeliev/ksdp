package com.shepeliev.ksdp

/**
 * A TimeDescription represents the fields present within a SDP time description.
 *
 * Quoting from RFC 2327:
 *
 *     Multiple "t=" fields may be used if a session is active at multiple
 *     irregularly spaced times; each additional
 *     "t=" field specifies an additional period of time for which the session
 *     will be active. If the session is active at
 *     regular times, an "r=" field (see below) should be used in addition to and
 *     following a "t=" field - in which
 *     case the "t=" field specifies the start and stop times of the repeat sequence.
 *
 * Please refer to IETF RFC 2327 for a description of SDP.
 */
public data class TimeDescription @Throws(SdpException::class) constructor(
    var time: Time,
    var repeats: MutableList<Repeat> = mutableListOf()
)

public val TimeDescription.lines: List<String>
    get() = buildList {
        add(time.line)
        repeats.forEach { add(it.line) }
    }
