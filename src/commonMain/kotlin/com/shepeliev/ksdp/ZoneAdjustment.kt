package com.shepeliev.ksdp

import com.shepeliev.ksdp.utils.toNtp
import kotlinx.datetime.Instant
import kotlin.time.Duration

public data class ZoneAdjustment(var time: Instant, var offset: Duration) {
    override fun toString(): String = "${time.toNtp()} ${offset.inWholeSeconds}"
}
