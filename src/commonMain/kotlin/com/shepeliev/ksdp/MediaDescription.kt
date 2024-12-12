package com.shepeliev.ksdp

/**
 * A MediaDescription identifies the set of medias that may be received on a specific port or set of ports. It includes:
 *
 *     a mediaType (e.g., audio, video, etc.)
 *     a port number (or set of ports)
 *     a protocol to be used (e.g., RTP/AVP)
 *     a set of media formats which correspond to Attributes associated with the media description.
 *
 * The following is an example
 *
 * m=audio 60000 RTP/AVP 0
 * a=rtpmap:0 PCMU/8000
 *
 * This example identifies that the client can receive audio on port 60000 in format 0 which corresponds to PCMU/8000.
 *
 * Please refer to IETF RFC 2327 for a description of SDP.
 */
public data class MediaDescription(
    var media: Media,
    var info: String? = null,
    var connection: Connection? = null,
    val bandwidth: MutableList<Bandwidth> = mutableListOf(),
    var key: Key? = null,
    val attributes: MutableList<Attribute> = mutableListOf()
) {
    override fun toString(): String = buildString {
        append("m=${media}\r\n")
        info?.let { append("i=$it\r\n") }
        connection?.let { append("c=$it\r\n") }
        bandwidth.forEach { append("b=$it\r\n") }
        key?.let { append("k=$it\r\n") }
        attributes.forEach { append("a=$it\r\n") }
    }
}
