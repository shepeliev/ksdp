package com.shepeliev.ksdp

import com.shepeliev.ksdp.utils.toNtp
import kotlinx.datetime.Instant

/**
 * A RepeatTime represents a t= field contained within a TimeDescription.
 *
 * A RepeatTime specifies the start and stop times for a SessionDescription.
 *
 * Note: this class uses [kotlinx.datetime.Instant] objects. SDP messages encode time in NTP
 * format.
 *
 * To convert between them use Long.ntpTimeToInstant() and Instant.toNtpTime().
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
public data class Time(val start: Instant, val end: Instant) {
    override fun toString(): String = "${start.toNtp()} ${end.toNtp()}"
}