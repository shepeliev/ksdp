package com.shepeliev.ksdp

import kotlin.test.Test
import kotlin.test.assertEquals

class VersionTest {
    @Test
    fun testEncode() {
        assertEquals("v=0", Version(0).encode())
    }

    @Test
    fun testParse() {
        assertEquals(Version(0), Field.parse("v=0"))
    }
}