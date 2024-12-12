package com.shepeliev.ksdp

import kotlin.time.Duration

public data class Repeat @Throws(SdpException::class) constructor(
    val interval: Duration,
    val activeDuration: Duration,
    val offsets: List<Duration> = listOf(Duration.ZERO)
) {
    init {
        checkIt(interval > Duration.ZERO) { "Interval must be greater than 0" }
        checkIt(activeDuration > Duration.ZERO) { "Active duration must be greater than 0" }
    }
}

internal val Repeat.line: String get() = buildString {
    append("r=")
    append(interval.inWholeSeconds)
    append(' ')
    append(activeDuration.inWholeSeconds)
    offsets.forEach {
        append(' ')
        append(it.inWholeSeconds)
    }
}
