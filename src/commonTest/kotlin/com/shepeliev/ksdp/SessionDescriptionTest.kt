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
            bandwidth = mutableListOf(Bandwidth("AS", 30), Bandwidth("RS", 30)),
            time = mutableListOf(
                TimeDescription(
                    time = Time(Instant.fromEpochSeconds(825434819), Instant.fromEpochSeconds(833473619)),
                    repeats = mutableListOf(
                        Repeat(interval = 7.days, activeDuration = 1.hours, offsets = listOf(ZERO, 25.hours)),
                        Repeat(interval = 1.days, activeDuration = 30.minutes, offsets = listOf(10.seconds, 25.hours)),
                    )
                )
            ),
            zoneAdjustments = mutableListOf(
                ZoneAdjustment(Instant.fromEpochSeconds(825434819), (-1).hours),
                ZoneAdjustment(Instant.fromEpochSeconds(833473619), ZERO),
            ),
            key = Key.Prompt,
            attributes = mutableListOf(
                Attribute.NameValue("group", "BUNDLE 0 1"),
                Attribute.Identity("extmap-allow-mixed"),
            ),
            mediaDescriptions = mutableListOf(
                MediaDescription(
                    media = Media("audio", 53710, "RTP/SAVPF", listOf("111", "8", "0"), portCount = 2),
                    info = "Audio title",
                    connection = Connection("224.2.17.12/127"),
                    bandwidth = mutableListOf(Bandwidth("AS", 30), Bandwidth("RS", 30)),
                    key = Key.Clear("password"),
                    attributes = mutableListOf(
                        Attribute.NameValue("rtpmap", "111 opus/48000/2"),
                        Attribute.NameValue("rtpmap", "8 PCMA/8000"),
                        Attribute.NameValue("fmtp", "111 minptime=10;useinbandfec=1"),
                        Attribute.NameValue("rtcp", "53711"),
                        Attribute.NameValue("rtcp-fb", "111 transport-cc"),
                        Attribute.NameValue("setup", "actpass"),
                        Attribute.NameValue("ptime", "20"),
                        Attribute.Identity("sendrecv"),
                    )
                ),
                MediaDescription(
                    media = Media("video", 50042, "RTP/SAVPF", listOf("96", "39")),
                    attributes = mutableListOf(
                        Attribute.NameValue("rtpmap", "96 VP8/90000"),
                        Attribute.NameValue("rtpmap", "39 H264/90000"),
                        Attribute.Identity("inactive"),
                    )
                )
            )
        )

        assertEquals(expectedSdp, TEST_PARSE_SDP.parseSdp())
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
            bandwidth = mutableListOf(Bandwidth("AS", 30), Bandwidth("RS", 30)),
            time = mutableListOf(
                TimeDescription(
                    time = Time(Instant.fromEpochSeconds(825434819), Instant.fromEpochSeconds(833473619)),
                    repeats = mutableListOf(
                        Repeat(interval = 7.days, activeDuration = 1.hours, offsets = listOf(ZERO, 25.hours)),
                        Repeat(interval = 1.days, activeDuration = 30.minutes, offsets = listOf(10.seconds, 25.hours)),
                    )
                )
            ),
            zoneAdjustments = mutableListOf(
                ZoneAdjustment(Instant.fromEpochSeconds(825434819), (-1).hours),
                ZoneAdjustment(Instant.fromEpochSeconds(833473619), ZERO),
            ),
            key = Key.Clear("password"),
            attributes = mutableListOf(
                Attribute.NameValue("group", "BUNDLE 0 1"),
                Attribute.Identity("extmap-allow-mixed"),
            ),
            mediaDescriptions = mutableListOf(
                MediaDescription(
                    media = Media("audio", 53710, "RTP/SAVPF", listOf("111", "8", "0"), portCount = 2),
                    info = "Audio title",
                    connection = Connection("224.2.17.12/127"),
                    bandwidth = mutableListOf(Bandwidth("AS", 30), Bandwidth("RS", 30)),
                    key = Key.Clear("password"),
                    attributes = mutableListOf(
                        Attribute.NameValue("rtpmap", "111 opus/48000/2"),
                        Attribute.NameValue("rtpmap", "8 PCMA/8000"),
                        Attribute.NameValue("fmtp", "111 minptime=10;useinbandfec=1"),
                        Attribute.NameValue("rtcp", "53711"),
                        Attribute.NameValue("rtcp-fb", "111 transport-cc"),
                        Attribute.NameValue("setup", "actpass"),
                        Attribute.NameValue("ptime", "20"),
                        Attribute.Identity("sendrecv"),
                    )
                ),
                MediaDescription(
                    media = Media("video", 50042, "RTP/SAVPF", listOf("96", "39")),
                    attributes = mutableListOf(
                        Attribute.NameValue("rtpmap", "96 VP8/90000"),
                        Attribute.NameValue("rtpmap", "39 H264/90000"),
                        Attribute.Identity("inactive"),
                    )
                )
            )

        )

        assertEquals(TEST_TO_STRING_SDP, sdp.sdp())
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
m=audio 53710/2 RTP/SAVPF 111 8 0\r
i=Audio title\r
c=IN IP4 224.2.17.12/127\r
b=AS:30\r
b=RS:30\r
k=clear:password\r
a=rtpmap:111 opus/48000/2\r
a=rtpmap:8 PCMA/8000\r
a=fmtp:111 minptime=10;useinbandfec=1\r
a=rtcp:53711\r
a=rtcp-fb:111 transport-cc\r
a=setup:actpass\r
a=ptime:20\r
a=sendrecv\r
m=video 50042 RTP/SAVPF 96 39\r
a=rtpmap:96 VP8/90000\r
a=rtpmap:39 H264/90000\r
a=inactive\r
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
m=audio 53710/2 RTP/SAVPF 111 8 0\r
i=Audio title\r
c=IN IP4 224.2.17.12/127\r
b=AS:30\r
b=RS:30\r
k=clear:password\r
a=rtpmap:111 opus/48000/2\r
a=rtpmap:8 PCMA/8000\r
a=fmtp:111 minptime=10;useinbandfec=1\r
a=rtcp:53711\r
a=rtcp-fb:111 transport-cc\r
a=setup:actpass\r
a=ptime:20\r
a=sendrecv\r
m=video 50042 RTP/SAVPF 96 39\r
a=rtpmap:96 VP8/90000\r
a=rtpmap:39 H264/90000\r
a=inactive\r
""".replace("\\r", "\r")
