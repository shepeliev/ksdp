package com.shepeliev.ksdp

import kotlinx.datetime.Instant
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.ZERO
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class SessionDescriptionTest {
    @Test
    fun testParse() {
        val expectedSdp = SessionDescription(
            version = 0,
            origin = Origin("jdoe", 2890844526, 2890842807, "10.47.16.5"),
            sessionName = "SDP Seminar",
            info = "A Seminar on the session description protocol",
            uri = "http://www.example.com/seminars/sdp.pdf",
            email = "j.doe@example.com (Jane Doe)",
            phone = "+1 617 555-5555",
            connection = Connection("224.2.17.12/127"),
            bandwidth = listOf(Bandwidth("AS", 30), Bandwidth("RS", 30)),
            time = listOf(
                TimeDescription(
                    time = Time(Instant.fromEpochSeconds(825434819), Instant.fromEpochSeconds(833473619)),
                    repeats = listOf(
                        Repeat(interval = 7.days, activeDuration = 1.hours, offsets = listOf(ZERO, 25.hours)),
                        Repeat(interval = 1.days, activeDuration = 30.minutes, offsets = listOf(10.seconds, 25.hours)),
                    )
                )
            ),
            zoneAdjustments = listOf(
                ZoneAdjustment(Instant.fromEpochSeconds(825434819), (-1).hours),
                ZoneAdjustment(Instant.fromEpochSeconds(833473619), ZERO),
            ),
            key = Key.Prompt,
            attributes = listOf(
                Attribute.NameValue("group","BUNDLE 0 1"),
                Attribute.Identity("extmap-allow-mixed"),
            ),
        )

        assertEquals(expectedSdp, TEST_PARSE_SDP.sessionDescription())
    }

    @Test
    fun testToString() {
        val sdp = SessionDescription(
            version = 0,
            origin = Origin("jdoe", 2890844526, 2890842807, "10.47.16.5"),
            sessionName = "SDP Seminar",
            info = "A Seminar on the session description protocol",
            uri = "http://www.example.com/seminars/sdp.pdf",
            email = "j.doe@example.com (Jane Doe)",
            phone = "+1 617 555-5555",
            connection = Connection("224.2.17.12/127"),
            bandwidth = listOf(Bandwidth("AS", 30), Bandwidth("RS", 30)),
            time = listOf(
                TimeDescription(
                    time = Time(Instant.fromEpochSeconds(825434819), Instant.fromEpochSeconds(833473619)),
                    repeats = listOf(
                        Repeat(interval = 7.days, activeDuration = 1.hours, offsets = listOf(ZERO, 25.hours)),
                        Repeat(interval = 1.days, activeDuration = 30.minutes, offsets = listOf(10.seconds, 25.hours)),
                    )
                )
            ),
            zoneAdjustments = listOf(
                ZoneAdjustment(Instant.fromEpochSeconds(825434819), (-1).hours),
                ZoneAdjustment(Instant.fromEpochSeconds(833473619), ZERO),
            ),
            key = Key.Clear("password"),
            attributes = listOf(
                Attribute.NameValue("group","BUNDLE 0 1"),
                Attribute.Identity("extmap-allow-mixed"),
            ),
        )

        assertEquals(TEST_TO_STRING_SDP, sdp.toString())
    }
}

private val TEST_PARSE_SDP = """v=0\r
o=jdoe 2890844526 2890842807 IN IP4 10.47.16.5\r
s=SDP Seminar\r
i=A Seminar on the session description protocol\r
u=http://www.example.com/seminars/sdp.pdf\r
e=j.doe@example.com (Jane Doe)\r
p=+1 617 555-5555\r
c=IN IP4 224.2.17.12/127\r
b=AS:30\r
b=RS:30\r
t=3034423619 3042462419\r
r=7d 1h 0 25h\r
r=1d 30m 10s 25h\r
z=3034423619 -1h 3042462419 0\r
k=prompt\r
a=group:BUNDLE 0 1\r
a=extmap-allow-mixed\r
""".replace("\\r", "\r")

private val TEST_TO_STRING_SDP = """v=0\r
o=jdoe 2890844526 2890842807 IN IP4 10.47.16.5\r
s=SDP Seminar\r
i=A Seminar on the session description protocol\r
u=http://www.example.com/seminars/sdp.pdf\r
e=j.doe@example.com (Jane Doe)\r
p=+1 617 555-5555\r
c=IN IP4 224.2.17.12/127\r
b=AS:30\r
b=RS:30\r
t=3034423619 3042462419\r
r=604800 3600 0 90000\r
r=86400 1800 10 90000\r
z=3034423619 -3600 3042462419 0\r
k=clear:password\r
a=group:BUNDLE 0 1\r
a=extmap-allow-mixed\r
""".replace("\\r", "\r")