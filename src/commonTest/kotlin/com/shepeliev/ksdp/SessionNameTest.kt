package com.shepeliev.ksdp

import org.junit.Test
import kotlin.test.assertEquals

class SessionNameTest {
    @Test
    fun testEncode() {
        assertEquals("s= ", SessionName().encode())
        assertEquals("s=bla bla bla", SessionName("bla bla bla").encode())
    }

    @Test
    fun testParse() {
        assertEquals(SessionName(), Field.parse("s= "))
        assertEquals(SessionName("bla bla bla"), Field.parse("s=bla bla bla"))
    }
}