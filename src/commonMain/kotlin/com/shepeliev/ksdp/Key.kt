package com.shepeliev.ksdp

/**
 * A Key represents the k= field contained within either a MediaDescription or a SessionDescription.
 *
 * Please refer to IETF RFC 2327 for a description of SDP.
 */
public sealed class Key(internal val method: String) {
    public data class Clear(val key: String) : Key("clear") {
        override fun toString(): String = "$method:$key"
    }

    public data class Base64(val key: String) : Key("base64") {
        override fun toString(): String = "$method:$key"
    }

    public data class Uri(val key: String) : Key("uri") {
        override fun toString(): String = "$method:$key"
    }

    public data object Prompt : Key("prompt") {
        override fun toString(): String = method
    }
}
