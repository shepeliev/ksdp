package com.shepeliev.ksdp

import org.junit.Test
import kotlin.test.assertEquals

class UriTest {
    @Test
    fun testToString() {
        assertEquals(
            "u=http://user:password@www.pierobon.org/iis/review1.htm.html#one",
            Uri("http://user:password@www.pierobon.org/iis/review1.htm.html#one").toString()
        )
        assertEquals(
            "u=http://a/b/c/g;x?y#s",
            Uri("http://a/b/c/g;x?y#s").toString()
        )
    }

    @Test
    fun testParse() {
        assertEquals(
            Uri("http://user:password@www.pierobon.org/iis/review1.htm.html#one"),
            "u=http://user:password@www.pierobon.org/iis/review1.htm.html#one".sdpField()
        )
    }

    @Test
    fun testUriFields() {
        val uri = "u=http://user:password@www.pierobon.org:8080/iis/?param=value#one".sdpField() as Uri
        assertEquals("http", uri.scheme)
        assertEquals("www.pierobon.org", uri.host)
        assertEquals(8080, uri.port)
        assertEquals("user:password", uri.userInfo)
        assertEquals("/iis/", uri.path)
        assertEquals("param=value", uri.query)
        assertEquals("one", uri.fragment)
    }
}