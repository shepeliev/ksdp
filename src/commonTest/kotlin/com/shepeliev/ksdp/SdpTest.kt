package com.shepeliev.ksdp

import com.github.h0tk3y.betterParse.parser.toParsedOrThrow
import com.github.h0tk3y.betterParse.parser.tryParseToEnd
import kotlin.test.Test
import kotlin.test.assertEquals

class SdpTest {
    private val tokenizer = Sdp.tokenizer

    @Test
    fun testIp4Address() {
        val ip4Adresses = listOf(
            "192.168.1.1",
            "0.0.0.0",
            "255.255.255.255",
        )

        ip4Adresses.forEach {
            val actual = runCatching {
                Sdp.ip4Address.tryParseToEnd(tokenizer.tokenize(it), 0).toParsedOrThrow().value
            }
            assertEquals(
                it,
                actual.getOrNull(),
                "Failed to parse $it: ${actual.exceptionOrNull()}"
            )
        }
    }

    @Test
    fun testIp6Address() {
        val ip6Adresses = listOf(
            "2001:0000:130F:0000:0000:09C0:876A:130B",
            "2001:FF42:130F::09C0:876A:130B",
            "2001:FF42:130F::09C0:876A",
            "2001:FF42:130F::09C0",
            "2001:FF42:130F::",
            "2001:FF42:130F::876A:130B",
            "2001:FF42:130F::130B",
            "2001:FF42:130F::",
        )

        ip6Adresses.forEach {
            val actual = runCatching {
                Sdp.ip6Address.tryParseToEnd(tokenizer.tokenize(it), 0).toParsedOrThrow().value
            }
            assertEquals(
                it,
                actual.getOrNull(),
                "Failed to parse $it: ${actual.exceptionOrNull()}"
            )
        }
    }

    @Test
    fun testUriIPV6Address() {
        val ip6Adresses = listOf(
            "2001:0000:130F:0000:0000:09C0:876A:130B",  // 6( h16 ":" ) ls32
            "::0000:130F:0000:0000:09C0:876A:130B",     // "::" 5( h16 ":" ) ls32
            "2001::130F:0000:0000:09C0:876A:130B",      // [               h16 ] "::" 4( h16 ":" ) ls32
            "0000::0000:0000:09C0:876A:130B",           // [ *1( h16 ":" ) h16 ] "::" 3( h16 ":" ) ls32
            "2001:0000::0000:0000:09C0:876A:130B",      // [ *1( h16 ":" ) h16 ] "::" 3( h16 ":" ) ls32
            "2001:db8::9:01",
        )

        ip6Adresses.forEach {
            val actual = runCatching {
                Sdp.IPv6Address.tryParseToEnd(tokenizer.tokenize(it), 0).toParsedOrThrow().value
            }
            assertEquals(
                it,
                actual.getOrNull(),
                "Failed to parse $it: ${actual.exceptionOrNull()}"
            )
        }
    }

    @Test
    fun testIp4MultiCast() {
        val ip4Multicasts = listOf(
            "224.255.255.255/123",
            "224.255.255.255/123/235425"
        )

        ip4Multicasts.forEach {
            val actual = runCatching {
                Sdp.ip4Multicast.tryParseToEnd(tokenizer.tokenize(it), 0).toParsedOrThrow().value
            }
            assertEquals(
                it,
                actual.getOrNull(),
                "Failed to parse $it: ${actual.exceptionOrNull()}"
            )
        }
    }

    @Test
    fun testIp6Multicast() {
        val ip6Multicasts = listOf(
            "2001:FF42:130F::09C0:876A:130B/123",
            "2001:FF42:130F::09C0:876A:130B/12353476547"
        )

        ip6Multicasts.forEach {
            val actual = runCatching {
                Sdp.ip6Multicast.tryParseToEnd(tokenizer.tokenize(it), 0).toParsedOrThrow().value
            }
            assertEquals(
                it,
                actual.getOrNull(),
                "Failed to parse $it: ${actual.exceptionOrNull()}"
            )
        }
    }

    @Test
    fun testFQDN() {
        val fqdns = listOf(
            "example.com",
        )

        fqdns.forEach {
            val actual = runCatching {
                Sdp.FQDN.tryParseToEnd(tokenizer.tokenize(it), 0).toParsedOrThrow().value
            }
            assertEquals(
                it,
                actual.getOrNull(),
                "Failed to parse $it: ${actual.exceptionOrNull()}"
            )
        }
    }

    @Test
    fun testMulticastAddress() {
        val multicastAddresses = listOf(
            "224.255.255.255/123/235425",
            "2001:FF42:130F::09C0:876A:130B/12399908908",
            "example.com",
        )

        multicastAddresses.forEach {
            val actual = runCatching {
                Sdp.multicastAddress.tryParseToEnd(tokenizer.tokenize(it), 0).toParsedOrThrow().value
            }
            assertEquals(
                it,
                actual.getOrNull(),
                "Failed to parse $it: ${actual.exceptionOrNull()}"
            )
        }
    }

    @Test
    fun testUnicastAddress() {
        val unicastAddresses = listOf(
            "224.255.255.255",
            "255:FF42:130F::09C0:876A:130B",
            "example.com",
        )

        unicastAddresses.forEach {
            val actual = runCatching {
                Sdp.unicastAddress.tryParseToEnd(tokenizer.tokenize(it), 0).toParsedOrThrow().value
            }
            assertEquals(
                it,
                actual.getOrNull(),
                "Failed to parse $it: ${actual.exceptionOrNull()}"
            )
        }
    }
}