package com.shepeliev.ksdp.utils

import kotlinx.datetime.Instant


/**
 * Converts Kotlin [Instant] to NTP time used in SDP.
 * The Network Time Protocol (NTP) is defined in RFC 1305.
 */
public fun Instant.toNtp(): Long = epochSeconds + NTP_TIME_OFFSET

/**
 * Converts NTP time used in SDP to Kotlin [Instant].
 * The Network Time Protocol (NTP) is defined in RFC 1305.
 */
public fun Long.ntpTimeToInstant(): Instant = Instant.fromEpochSeconds(this - NTP_TIME_OFFSET)

private const val NTP_TIME_OFFSET = 2208988800L

