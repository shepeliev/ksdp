package com.shepeliev.ksdp

import kotlin.test.Test
import kotlin.test.assertEquals

class OriginTest {
    @Test
    fun testEncode() {
        val origin = Origin("example", 1, 2, "IN", "IP4", "192.6.55.78")
        assertEquals("o=example 1 2 IN IP4 192.6.55.78", origin.encode())
    }

    @Test
    fun testParse() {
        val origin = Field.parse("o=root 1913139612 1913139612 IN IP4 0.0.0.0")
        assertEquals(Origin("root", 1913139612, 1913139612, "IN", "IP4", "0.0.0.0"), origin)
    }
}