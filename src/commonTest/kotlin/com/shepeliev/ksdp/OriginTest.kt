package com.shepeliev.ksdp

import kotlin.test.Test
import kotlin.test.assertEquals

class OriginTest {
    @Test
    fun testEncode() {
        val origin = Origin("example", 1, 2, "192.6.55.78", "IN", "IP4")
        assertEquals("o=example 1 2 IN IP4 192.6.55.78", origin.toString())
    }

    @Test
    fun testParse() {
        assertEquals(
            Origin("root", 1913139612, 1913139612, "0.0.0.0", "IN", "IP4"),
            "o=root 1913139612 1913139612 IN IP4 0.0.0.0".sdpField())
    }
}