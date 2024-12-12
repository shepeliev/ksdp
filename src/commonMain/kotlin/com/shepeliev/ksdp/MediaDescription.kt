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
    var bandwidth: MutableList<Bandwidth> = mutableListOf(),
    var key: Key? = null,
    var attributes: MutableList<Attribute> = mutableListOf()
)

internal val MediaDescription.lines: List<String>
    get() = buildList {
        add("m=${media}")
        info?.let { add("i=$it") }
        connection?.let { add(it.line) }
        bandwidth.forEach { add(it.line) }
        key?.let { add(it.line) }
        attributes.forEach { add(it.line) }
    }
