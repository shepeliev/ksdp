package com.shepeliev.ksdp

import org.junit.Test
import kotlin.test.assertEquals

class InfoTest {
    @Test
    fun testEncode() {
        assertEquals("i=bla bla bla", Info("bla bla bla").toString())
    }

    @Test
    fun testParse() {
        assertEquals(Info("bla bla bla"), "i=bla bla bla".sdpField())
    }
}