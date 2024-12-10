package com.shepeliev.ksdp

data class SessionDescription(
    val origin: Origin,
    val sessionName: SessionName,
    val info: Info?,
    val uri: Uri?,
    val version: Version = Version(0),
) {
    override fun toString(): String = buildString {
        append(version)
        append("\r\n")
        append(origin)
        append("\r\n")
        append(sessionName)
        append("\r\n")
        info?.let {
            append(it)
            append("\r\n")
        }
        uri?.let {
            append(it)
            append("\r\n")
        }
    }
}

fun String.sessionDescription(): SessionDescription {
    var v: Version? = null
    var o: Origin? = null
    var s: SessionName? = null
    var i: Info? = null
    var u: Uri? = null

    trim().lines()
        .mapIndexed { index, line -> line.sdpField(index + 1) }
        .forEach {
            when (it) {
                is Version -> v = it
                is Origin -> o = it
                is SessionName -> s = it
                is Info -> i = it
                is Uri -> u = it
            }
        }

    when {
        v == null -> throw SdpParseException("SDP malformed: \"v\" field is required.")
        o == null -> throw SdpParseException("SDP malformed: \"o\" field is required.")
        s == null -> throw SdpParseException("SDP malformed: \"s\" field is required.")
    }

    return SessionDescription(
        version = v!!,
        origin = o!!,
        sessionName = s!!,
        info = i,
        uri = u,
    )
}