package com.shepeliev.ksdp

import org.junit.Test
import kotlin.test.assertEquals

class SessionNameTest {
    @Test
    fun testEncode() {
        assertEquals("s= ", SessionName().toString())
        assertEquals("s=bla bla bla", SessionName("bla bla bla").toString())
    }

    @Test
    fun testParse() {
        assertEquals(SessionName(), "s= ".sdpField())
        assertEquals(SessionName("bla bla bla"), "s=bla bla bla".sdpField())
    }
}