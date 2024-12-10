package com.shepeliev.ksdp

import kotlin.test.Test
import kotlin.test.assertEquals

class SessionDescriptionTest {
    @Test
    fun testParse() {
        val sdp = SDP.sessionDescription()

        assertEquals(Version(0), sdp.version)
        assertEquals(
            Origin("jdoe", 2890844526, 2890842807, "10.47.16.5"),
            sdp.origin
        )
        assertEquals(SessionName("SDP Seminar"), sdp.sessionName)
        assertEquals(Info("A Seminar on the session description protocol"), sdp.info)
        assertEquals(Uri("http://www.example.com/seminars/sdp.pdf"), sdp.uri)
    }

    @Test
    fun tesToString() {
        val sdp = SessionDescription(
            origin = Origin("jdoe", 2890844526, 2890842807, "10.47.16.5"),
            sessionName = SessionName("SDP Seminar"),
            info = Info("A Seminar on the session description protocol"),
            uri = Uri("http://www.example.com/seminars/sdp.pdf")
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