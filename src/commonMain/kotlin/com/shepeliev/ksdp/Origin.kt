package com.shepeliev.ksdp

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
    val username: String,
    val sessionId: Long,
    val sessionVersion: Long,
    val address: String,
    val networkType: String = "IN",
    val addressType: String = "IP4",
) {
    override fun toString(): String = "$username $sessionId $sessionVersion $networkType $addressType $address"
}
