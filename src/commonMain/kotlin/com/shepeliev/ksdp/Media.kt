package com.shepeliev.ksdp

/**
 * A Media represents an m= field contained within a MediaDescription. The Media
 * identifies information about the format(s) of the
 * media associated with the MediaDescription.
 *
 * The Media field includes:
 *
 *     a mediaType (e.g. audio, video, etc.)
 *     a port number (or set of ports)
 *     a protocol to be used (e.g. RTP/AVP)
 *     a set of media formats which correspond to Attributes associated with the
 * media description.
 *
 * Here is an example:
 *
 * m=audio 60000 RTP/AVP 0
 * a=rtpmap:0 PCMU/8000
 *
 * This example identifies that the client can receive audio on port 60000 in
 * format 0 which corresponds to PCMU/8000.
 *
 * Please refer to IETF RFC 2327 for a description of SDP.
 */
public data class Media @Throws(SdpException::class) constructor(
    var type: String,
    var port: Int,
    var protocol: String,
    var formats: List<String>,
    var portCount: Int = 1,
) {
    init {
        checkIt(port >= 0) { "Port must be non-negative" }
        checkIt(portCount >= 1) { "Port count must be positive" }
    }

    override fun toString(): String {
        val portCountText = if (portCount > 1) "/$portCount" else ""
        val formatsText = formats.joinToString(" ")
        return "$type $port$portCountText $protocol $formatsText"
    }
}
