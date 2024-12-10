package com.shepeliev.ksdp

import com.github.h0tk3y.betterParse.parser.tryParseToEnd


fun main(args: Array<String>) {
    val expr = "https://asd@"
    println("Tokens: ${Sdp.tokens.size}")
    println(Sdp.ip4Address.tryParseToEnd(Sdp.tokenizer.tokenize("192.168.1.1"), 0))
    println(Sdp.hexseq.tryParseToEnd(Sdp.tokenizer.tokenize("2001:FF42:130F"), 0))
    println(Sdp.ip6Address.tryParseToEnd(Sdp.tokenizer.tokenize("2001:0000:130F:0000:0000:09C0:876A:130B"), 0))
    println(Sdp.ip4Multicast.tryParseToEnd(Sdp.tokenizer.tokenize("224.255.255.255/123/235425"), 0))
//    val scheme = runCatching {    }
//        .onFailure { println("Error parsing scheme: ${it.message}") }
//        .getOrNull()
//    println(scheme)
}