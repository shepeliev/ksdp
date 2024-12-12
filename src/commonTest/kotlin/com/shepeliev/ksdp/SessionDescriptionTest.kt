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
            origin = Origin("jdoe", sessionId = 2890844526, sessionVersion = 2890842807, address = "10.47.16.5"),
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
                    media = Media("audio", 53710, "RTP/SAVPF", mutableListOf("111", "8", "0"), portCount = 2),
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
                    media = Media("video", 50042, "RTP/SAVPF", mutableListOf("96", "39")),
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
            origin = Origin("jdoe", sessionId = 2890844526, sessionVersion = 2890842807, address = "10.47.16.5"),
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
                    media = Media("audio", 53710, "RTP/SAVPF", mutableListOf("111", "8", "0"), portCount = 2),
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
                    media = Media("video", 50042, "RTP/SAVPF", mutableListOf("96", "39")),
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

    @Test
    fun testAlac() = test("alac")

    @Test
    fun testBfcp() = test("bfcp")

    @Test
    fun testDanteAes67() = test("dante-aes67")

    @Test
    fun testExtmapEncrypt() = test("extmap-encrypt")

    @Test
    fun testHacky() = test("hacky")

    @Test
    fun testIcelite() = test("icelite")

    @Test
    fun testInvalid() = test("invalid")

    @Test
    fun testJsep() = test("jsep")

    @Test
    fun testJssip() = test("jssip")

    @Test
    fun testMediaclkAvbtp() = test("mediaclk-avbtp")

    @Test
    fun testMediaclkPtpV2() = test("mediaclk-ptp-v2")

    @Test
    fun testMediaclkPtpV2WRate() = test("mediaclk-ptp-v2-w-rate")

    @Test
    fun testMediaclkRtp() = test("mediaclk-rtp")

    @Test
    fun testNormal() = test("normal")

    @Test
    fun testOnvif() = test("onvif")

    @Test
    fun testRtcpFb() = test("rtcp-fb")

    @Test
    fun testSctpDtls26() = test("sctp-dtls-26")

    @Test
    fun testSimulcast() = test("simulcast")

    @Test
    fun testSsrc() = test("ssrc")

    @Test
    fun testSt20226() = test("st2022-6")

    @Test
    fun testSt211020() = test("st2110-20")

    @Test
    fun testTcpActive() = test("tcp-active")

    @Test
    fun testTcpPassive() = test("tcp-passive")

    @Test
    fun testTsRefclkMedia() = test("ts-refclk-media")

    @Test
    fun testTsRefclkSess() = test("ts-refclk-sess")

    private fun test(sdpName: String) {
        val sdp = readSdpFile(sdpName)
        val sessionDescription = sdp.parseSdp()
        val composedSdp = sessionDescription.sdp(lineSeparator = "\n")

        assertEquals(sessionDescription, composedSdp.parseSdp())
    }
}
