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

    public class Unknown(method: String, public val key: String?) : Key(method) {
        override fun toString(): String = "$method:${key ?: ""}"

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Unknown) return false

            if (key != other.key) return false

            return true
        }

        override fun hashCode(): Int {
            return key?.hashCode() ?: 0
        }
    }
}
