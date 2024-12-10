package com.shepeliev.ksdp

sealed class Field(val type: Type) {
    enum class Type(val type: Char) {
        VERSION('v'),
        ORIGIN('o'),
        SESSION_NAME('s'),
        INFORMATION('i'),
        URI('u'),
        EMAIL('e'),
        PHONE('p'),
        CONNECTION('c'),
        BANDWIDTH('b'),
        TIME('t'),
        REPEAT_TIMES('r'),
        TIME_ZONE('z'),
        ENCRYPTION_KEY('k'),
        ATTRIBUTE('a'),
        MEDIA('m'),
    }
}

internal fun String.sdpField(lineNumber: Int = 1): Field {
    if (isBlank()) {
        throw SdpParseException("Parse SDP at line #$lineNumber failed: blank or empty text.")
    }
    return when (val lineType = firstOrNull()) {
        Field.Type.VERSION.type -> Version(this, lineNumber)
        Field.Type.ORIGIN.type -> Origin(this, lineNumber)
        Field.Type.SESSION_NAME.type -> SessionName(this, lineNumber)
        else -> throw SdpParseException("Parse SDP at line #$lineNumber failed: unknown field type \"$lineType=\".")
    }
}

