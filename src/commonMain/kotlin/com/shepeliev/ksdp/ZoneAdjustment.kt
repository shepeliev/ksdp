package com.shepeliev.ksdp

import com.shepeliev.ksdp.utils.toNtp
import kotlinx.datetime.Instant
import kotlin.time.Duration

public data class ZoneAdjustment(val time: Instant, val offset: Duration) {
    override fun toString(): String = "${time.toNtp()} ${offset.inWholeSeconds}"
}
