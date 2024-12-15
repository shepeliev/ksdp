package com.shepeliev.ksdp

/**
 * An Attribute represents an a= fields contained within either a MediaDescription or a
 * SessionDescription.
 *
 * An Attribute can be just an identity/name or a name-value pair.
 *
 * Here are some examples:
 *
 * a=recvonly
 *     identifies a rcvonly attribute with just a name
 * a=rtpmap:0 PCMU/8000
 *     identifies the media format 0 has having the value PCMU/8000.
 *
 * If a value is present, it must be preceeded by the : character.
 */
public sealed interface Attribute {
    public val name: String

    public data class Identity(override val name: String) : Attribute
    public data class NameValue(override val name: String, val value: String) : Attribute
    public data class Invalid(override val name: String, val line: String) : Attribute
}

public val Attribute.line: String get() = when (this) {
    is Attribute.Identity -> "a=$name"
    is Attribute.NameValue -> "a=$name:$value"
    is Attribute.Invalid -> line
}
