package com.shepeliev.ksdp

import kotlin.test.Test
import kotlin.test.assertEquals

class SessionDescriptionTest {
    @Test
    fun testParse() {
        val expectedSdp = SessionDescription(
            version = 0,
            origin = Origin("jdoe", 2890844526, 2890842807, "10.47.16.5"),
            sessionName = "SDP Seminar",
            info = "A Seminar on the session description protocol",
            uri = "http://www.example.com/seminars/sdp.pdf"
        )

        assertEquals(expectedSdp, SDP.sessionDescription())
    }

    @Test
    fun tesToString() {
        val sdp = SessionDescription(
            version = 0,
            origin = Origin("jdoe", 2890844526, 2890842807, "10.47.16.5"),
            sessionName = "SDP Seminar",
            info = "A Seminar on the session description protocol",
            uri = "http://www.example.com/seminars/sdp.pdf"
        )

        assertEquals(SDP, sdp.toString())
    }
}

private val SDP = """v=0\r
o=jdoe 2890844526 2890842807 IN IP4 10.47.16.5\r
s=SDP Seminar\r
i=A Seminar on the session description protocol\r
u=http://www.example.com/seminars/sdp.pdf\r
""".replace("\\r", "\r")