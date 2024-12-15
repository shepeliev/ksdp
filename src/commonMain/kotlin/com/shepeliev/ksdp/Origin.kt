package com.shepeliev.ksdp

import com.shepeliev.ksdp.utils.toNtp
import kotlinx.datetime.Clock

/**
 * An Origin represents the o= fields contained within a SessionDescription.
 *
 * The Origin field identifies the originator of the session.
 *
 * This is not necessarily the same entity who is involved in the session.
 *
 * The Origin contains:
 *
 *     the name of the user originating the session,
 *     a unique session identifier, and
 *     a unique version for the session.
 *
 * These fields should uniquely identify the session.
 *
 * The Origin also includes:
 *
 *     the network type,
 *     address type, and
 *     address of the originator.
 *
 * Please refer to IETF RFC 2327 for a description of SDP.
 */
public data class Origin(
    var username: String,
    var address: String,
    var sessionId: Long = Clock.System.now().toNtp(),
    var sessionVersion: Long = Clock.System.now().toNtp(),
    var networkType: String = "IN",
    var addressType: String = "IP4",
)

public val Origin.line: String
    get() = "o=$username $sessionId $sessionVersion $networkType $addressType $address"
