package com.shepeliev.ksdp

import kotlin.test.Test
import kotlin.test.assertEquals

class VersionTest {
    @Test
    fun testToString() {
        assertEquals("v=0", Version(0).toString())
    }

    @Test
    fun testParse() {
        assertEquals(Version(0), "v=0".sdpField())
    }
}