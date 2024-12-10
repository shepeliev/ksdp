package com.shepeliev.ksdp

import com.github.h0tk3y.betterParse.combinators.*
import com.github.h0tk3y.betterParse.grammar.Grammar
import com.github.h0tk3y.betterParse.lexer.CharToken
import com.github.h0tk3y.betterParse.lexer.TokenMatch
import com.github.h0tk3y.betterParse.lexer.literalToken
import com.github.h0tk3y.betterParse.parser.Parser
import com.github.h0tk3y.betterParse.utils.*
import com.shepeliev.ksdp.uri.Authority

// format: off
data class Sdp(
    val scheme: String,
    val authority: Authority,
    val path: String?,
) {
    companion object : Grammar<Sdp>() {
        private val x00 by CharToken(name = "x00", text = Char(0x00)) // NUL
        private val x01 by CharToken(name = "x01", text = Char(0x01)) // SOH
        private val x02 by CharToken(name = "x02", text = Char(0x02)) // STX
        private val x03 by CharToken(name = "x03", text = Char(0x03)) // ETX
        private val x04 by CharToken(name = "x04", text = Char(0x04)) // EOT
        private val x05 by CharToken(name = "x05", text = Char(0x05)) // ENQ
        private val x06 by CharToken(name = "x06", text = Char(0x06)) // ACK
        private val x07 by CharToken(name = "x07", text = Char(0x07)) // BEL
        private val x08 by CharToken(name = "x08", text = Char(0x08)) // BS
        private val x09 by CharToken(name = "x09", text = Char(0x09)) // HT
        private val x0A by CharToken(name = "x0A", text = Char(0x0A)) // LF
        private val x0B by CharToken(name = "x0B", text = Char(0x0B)) // VT
        private val x0C by CharToken(name = "x0C", text = Char(0x0C)) // FF
        private val x0D by CharToken(name = "x0D", text = Char(0x0D)) // CR
        private val x0E by CharToken(name = "x0E", text = Char(0x0E)) // SO
        private val x0F by CharToken(name = "x0F", text = Char(0x0F)) // SI
        private val x10 by CharToken(name = "x10", text = Char(0x10)) // DLE
        private val x11 by CharToken(name = "x11", text = Char(0x11)) // DC1
        private val x12 by CharToken(name = "x12", text = Char(0x12)) // DC2
        private val x13 by CharToken(name = "x13", text = Char(0x13)) // DC3
        private val x14 by CharToken(name = "x14", text = Char(0x14)) // DC4
        private val x15 by CharToken(name = "x15", text = Char(0x15)) // NAK
        private val x16 by CharToken(name = "x16", text = Char(0x16)) // SYN
        private val x17 by CharToken(name = "x17", text = Char(0x17)) // ETB
        private val x18 by CharToken(name = "x18", text = Char(0x18)) // CAN
        private val x19 by CharToken(name = "x19", text = Char(0x19)) // EM
        private val x1A by CharToken(name = "x1A", text = Char(0x1A)) // SUB
        private val x1B by CharToken(name = "x1B", text = Char(0x1B)) // ESC
        private val x1C by CharToken(name = "x1C", text = Char(0x1C)) // FS
        private val x1D by CharToken(name = "x1D", text = Char(0x1D)) // GS
        private val x1E by CharToken(name = "x1E", text = Char(0x1E)) // RS
        private val x1F by CharToken(name = "x1F", text = Char(0x1F)) // US
        private val x20 by CharToken(name = "x20", text = Char(0x20)) // SP
        private val x21 by CharToken(name = "!", text = Char(0x21)) // !
        private val x22 by CharToken(name = "\"", text = Char(0x22)) // "
        private val x23 by CharToken(name = "#", text = Char(0x23)) // #
        private val x24 by CharToken(name = "$", text = Char(0x24)) // $
        private val x25 by CharToken(name = "%", text = Char(0x25)) // %
        private val x26 by CharToken(name = "&", text = Char(0x26)) // &
        private val x27 by CharToken(name = "'", text = Char(0x27)) // '
        private val x28 by CharToken(name = "(", text = Char(0x28)) // (
        private val x29 by CharToken(name = ")", text = Char(0x29)) // )
        private val x2A by CharToken(name = "*", text = Char(0x2A)) // *
        private val x2B by CharToken(name = "+", text = Char(0x2B)) // +
        private val x2C by CharToken(name = ",", text = Char(0x2C)) // ,
        private val x2D by CharToken(name = "-", text = Char(0x2D)) // -
        private val x2E by CharToken(name = ".", text = Char(0x2E)) // .
        private val x2F by CharToken(name = "/", text = Char(0x2F)) // /
        private val x30 by CharToken(name = "0", text = Char(0x30)) // 0
        private val x31 by CharToken(name = "1", text = Char(0x31)) // 1
        private val x32 by CharToken(name = "2", text = Char(0x32)) // 2
        private val x33 by CharToken(name = "3", text = Char(0x33)) // 3
        private val x34 by CharToken(name = "4", text = Char(0x34)) // 4
        private val x35 by CharToken(name = "5", text = Char(0x35)) // 5
        private val x36 by CharToken(name = "6", text = Char(0x36)) // 6
        private val x37 by CharToken(name = "7", text = Char(0x37)) // 7
        private val x38 by CharToken(name = "8", text = Char(0x38)) // 8
        private val x39 by CharToken(name = "9", text = Char(0x39)) // 9
        private val x3A by CharToken(name = ":", text = Char(0x3A)) // :
        private val x3B by CharToken(name = ";", text = Char(0x3B)) // ;
        private val x3C by CharToken(name = "<", text = Char(0x3C)) // <
        private val x3D by CharToken(name = "=", text = Char(0x3D)) // =
        private val x3E by CharToken(name = ">", text = Char(0x3E)) // >
        private val x3F by CharToken(name = "?", text = Char(0x3F)) // ?
        private val x40 by CharToken(name = "@", text = Char(0x40)) // @
        private val x41 by CharToken(name = "A", text = Char(0x41)) // A
        private val x42 by CharToken(name = "B", text = Char(0x42)) // B
        private val x43 by CharToken(name = "C", text = Char(0x43)) // C
        private val x44 by CharToken(name = "D", text = Char(0x44)) // D
        private val x45 by CharToken(name = "E", text = Char(0x45)) // E
        private val x46 by CharToken(name = "F", text = Char(0x46)) // F
        private val x47 by CharToken(name = "G", text = Char(0x47)) // G
        private val x48 by CharToken(name = "H", text = Char(0x48)) // H
        private val x49 by CharToken(name = "I", text = Char(0x49)) // I
        private val x4A by CharToken(name = "J", text = Char(0x4A)) // J
        private val x4B by CharToken(name = "K", text = Char(0x4B)) // K
        private val x4C by CharToken(name = "L", text = Char(0x4C)) // L
        private val x4D by CharToken(name = "M", text = Char(0x4D)) // M
        private val x4E by CharToken(name = "N", text = Char(0x4E)) // N
        private val x4F by CharToken(name = "O", text = Char(0x4F)) // O
        private val x50 by CharToken(name = "P", text = Char(0x50)) // P
        private val x51 by CharToken(name = "Q", text = Char(0x51)) // Q
        private val x52 by CharToken(name = "R", text = Char(0x52)) // R
        private val x53 by CharToken(name = "S", text = Char(0x53)) // S
        private val x54 by CharToken(name = "T", text = Char(0x54)) // T
        private val x55 by CharToken(name = "U", text = Char(0x55)) // U
        private val x56 by CharToken(name = "V", text = Char(0x56)) // V
        private val x57 by CharToken(name = "W", text = Char(0x57)) // W
        private val x58 by CharToken(name = "X", text = Char(0x58)) // X
        private val x59 by CharToken(name = "Y", text = Char(0x59)) // Y
        private val x5A by CharToken(name = "Z", text = Char(0x5A)) // Z
        private val x5B by CharToken(name = "[", text = Char(0x5B)) // [
        private val x5C by CharToken(name = "\\", text = Char(0x5C)) // \
        private val x5D by CharToken(name = "]", text = Char(0x5D)) // ]
        private val x5E by CharToken(name = "^", text = Char(0x5E)) // ^
        private val x5F by CharToken(name = "_", text = Char(0x5F)) // _
        private val x60 by CharToken(name = "`", text = Char(0x60)) // `
        private val x61 by CharToken(name = "a", text = Char(0x61)) // a
        private val x62 by CharToken(name = "b", text = Char(0x62)) // b
        private val x63 by CharToken(name = "c", text = Char(0x63)) // c
        private val x64 by CharToken(name = "d", text = Char(0x64)) // d
        private val x65 by CharToken(name = "e", text = Char(0x65)) // e
        private val x66 by CharToken(name = "f", text = Char(0x66)) // f
        private val x67 by CharToken(name = "g", text = Char(0x67)) // g
        private val x68 by CharToken(name = "h", text = Char(0x68)) // h
        private val x69 by CharToken(name = "i", text = Char(0x69)) // i
        private val x6A by CharToken(name = "j", text = Char(0x6A)) // j
        private val x6B by CharToken(name = "k", text = Char(0x6B)) // k
        private val x6C by CharToken(name = "l", text = Char(0x6C)) // l
        private val x6D by CharToken(name = "m", text = Char(0x6D)) // m
        private val x6E by CharToken(name = "n", text = Char(0x6E)) // n
        private val x6F by CharToken(name = "o", text = Char(0x6F)) // o
        private val x70 by CharToken(name = "p", text = Char(0x70)) // p
        private val x71 by CharToken(name = "q", text = Char(0x71)) // q
        private val x72 by CharToken(name = "r", text = Char(0x72)) // r
        private val x73 by CharToken(name = "s", text = Char(0x73)) // s
        private val x74 by CharToken(name = "t", text = Char(0x74)) // t
        private val x75 by CharToken(name = "u", text = Char(0x75)) // u
        private val x76 by CharToken(name = "v", text = Char(0x76)) // v
        private val x77 by CharToken(name = "w", text = Char(0x77)) // w
        private val x78 by CharToken(name = "x", text = Char(0x78)) // x
        private val x79 by CharToken(name = "y", text = Char(0x79)) // y
        private val x7A by CharToken(name = "z", text = Char(0x7A)) // z
        private val x7B by CharToken(name = "{", text = Char(0x7B)) // {
        private val x7C by CharToken(name = "|", text = Char(0x7C)) // |
        private val x7D by CharToken(name = "}", text = Char(0x7D)) // }
        private val x7E by CharToken(name = "~", text = Char(0x7E)) // ~
        private val x7F by CharToken(name = "x7F", text = Char(0x7F)) // DEL
        private val x80 by CharToken(name = "x80", text = Char(0x80) /* <control-80> */)
        private val x81 by CharToken(name = "x81", text = Char(0x81) /* <control-81> */)
        private val x82 by CharToken(name = "x82", text = Char(0x82) /* <control-82> */)
        private val x83 by CharToken(name = "x83", text = Char(0x83) /* <control-83> */)
        private val x84 by CharToken(name = "x84", text = Char(0x84) /* <control-84> */)
        private val x85 by CharToken(name = "x85", text = Char(0x85) /* <control-85> */)
        private val x86 by CharToken(name = "x86", text = Char(0x86) /* <control-86> */)
        private val x87 by CharToken(name = "x87", text = Char(0x87) /* <control-87> */)
        private val x88 by CharToken(name = "x88", text = Char(0x88) /* <control-88> */)
        private val x89 by CharToken(name = "x89", text = Char(0x89) /* <control-89> */)
        private val x8A by CharToken(name = "x8A", text = Char(0x8A) /* <control-8A> */)
        private val x8B by CharToken(name = "x8B", text = Char(0x8B) /* <control-8B> */)
        private val x8C by CharToken(name = "x8C", text = Char(0x8C) /* <control-8C> */)
        private val x8D by CharToken(name = "x8D", text = Char(0x8D) /* <control-8D> */)
        private val x8E by CharToken(name = "x8E", text = Char(0x8E) /* <control-8E> */)
        private val x8F by CharToken(name = "x8F", text = Char(0x8F) /* <control-8F> */)
        private val x90 by CharToken(name = "x90", text = Char(0x90) /* <control-90> */)
        private val x91 by CharToken(name = "x91", text = Char(0x91) /* <control-91> */)
        private val x92 by CharToken(name = "x92", text = Char(0x92) /* <control-92> */)
        private val x93 by CharToken(name = "x93", text = Char(0x93) /* <control-93> */)
        private val x94 by CharToken(name = "x94", text = Char(0x94) /* <control-94> */)
        private val x95 by CharToken(name = "x95", text = Char(0x95) /* <control-95> */)
        private val x96 by CharToken(name = "x96", text = Char(0x96) /* <control-96> */)
        private val x97 by CharToken(name = "x97", text = Char(0x97) /* <control-97> */)
        private val x98 by CharToken(name = "x98", text = Char(0x98) /* <control-98> */)
        private val x99 by CharToken(name = "x99", text = Char(0x99) /* <control-99> */)
        private val x9A by CharToken(name = "x9A", text = Char(0x9A) /* <control-9A> */)
        private val x9B by CharToken(name = "x9B", text = Char(0x9B) /* <control-9B> */)
        private val x9C by CharToken(name = "x9C", text = Char(0x9C) /* <control-9C> */)
        private val x9D by CharToken(name = "x9D", text = Char(0x9D) /* <control-9D> */)
        private val x9E by CharToken(name = "x9E", text = Char(0x9E) /* <control-9E> */)
        private val x9F by CharToken(name = "x9F", text = Char(0x9F) /* <control-9F> */)
        private val xA0 by CharToken(name = "xA0", text = Char(0xA0) /* <control-A0> */)
        private val xA1 by CharToken(name = "xA1", text = Char(0xA1) /* <control-A1> */)
        private val xA2 by CharToken(name = "xA2", text = Char(0xA2) /* <control-A2> */)
        private val xA3 by CharToken(name = "xA3", text = Char(0xA3) /* <control-A3> */)
        private val xA4 by CharToken(name = "xA4", text = Char(0xA4) /* <control-A4> */)
        private val xA5 by CharToken(name = "xA5", text = Char(0xA5) /* <control-A5> */)
        private val xA6 by CharToken(name = "xA6", text = Char(0xA6) /* <control-A6> */)
        private val xA7 by CharToken(name = "xA7", text = Char(0xA7) /* <control-A7> */)
        private val xA8 by CharToken(name = "xA8", text = Char(0xA8) /* <control-A8> */)
        private val xA9 by CharToken(name = "xA9", text = Char(0xA9) /* <control-A9> */)
        private val xAA by CharToken(name = "xAA", text = Char(0xAA) /* <control-AA> */)
        private val xAB by CharToken(name = "xAB", text = Char(0xAB) /* <control-AB> */)
        private val xAC by CharToken(name = "xAC", text = Char(0xAC) /* <control-AC> */)
        private val xAD by CharToken(name = "xAD", text = Char(0xAD) /* <control-AD> */)
        private val xAE by CharToken(name = "xAE", text = Char(0xAE) /* <control-AE> */)
        private val xAF by CharToken(name = "xAF", text = Char(0xAF) /* <control-AF> */)
        private val xB0 by CharToken(name = "xB0", text = Char(0xB0) /* <control-B0> */)
        private val xB1 by CharToken(name = "xB1", text = Char(0xB1) /* <control-B1> */)
        private val xB2 by CharToken(name = "xB2", text = Char(0xB2) /* <control-B2> */)
        private val xB3 by CharToken(name = "xB3", text = Char(0xB3) /* <control-B3> */)
        private val xB4 by CharToken(name = "xB4", text = Char(0xB4) /* <control-B4> */)
        private val xB5 by CharToken(name = "xB5", text = Char(0xB5) /* <control-B5> */)
        private val xB6 by CharToken(name = "xB6", text = Char(0xB6) /* <control-B6> */)
        private val xB7 by CharToken(name = "xB7", text = Char(0xB7) /* <control-B7> */)
        private val xB8 by CharToken(name = "xB8", text = Char(0xB8) /* <control-B8> */)
        private val xB9 by CharToken(name = "xB9", text = Char(0xB9) /* <control-B9> */)
        private val xBA by CharToken(name = "xBA", text = Char(0xBA) /* <control-BA> */)
        private val xBB by CharToken(name = "xBB", text = Char(0xBB) /* <control-BB> */)
        private val xBC by CharToken(name = "xBC", text = Char(0xBC) /* <control-BC> */)
        private val xBD by CharToken(name = "xBD", text = Char(0xBD) /* <control-BD> */)
        private val xBE by CharToken(name = "xBE", text = Char(0xBE) /* <control-BE> */)
        private val xBF by CharToken(name = "xBF", text = Char(0xBF) /* <control-BF> */)
        private val xC0 by CharToken(name = "xC0", text = Char(0xC0) /* <control-C0> */)
        private val xC1 by CharToken(name = "xC1", text = Char(0xC1) /* <control-C1> */)
        private val xC2 by CharToken(name = "xC2", text = Char(0xC2) /* <control-C2> */)
        private val xC3 by CharToken(name = "xC3", text = Char(0xC3) /* <control-C3> */)
        private val xC4 by CharToken(name = "xC4", text = Char(0xC4) /* <control-C4> */)
        private val xC5 by CharToken(name = "xC5", text = Char(0xC5) /* <control-C5> */)
        private val xC6 by CharToken(name = "xC6", text = Char(0xC6) /* <control-C6> */)
        private val xC7 by CharToken(name = "xC7", text = Char(0xC7) /* <control-C7> */)
        private val xC8 by CharToken(name = "xC8", text = Char(0xC8) /* <control-C8> */)
        private val xC9 by CharToken(name = "xC9", text = Char(0xC9) /* <control-C9> */)
        private val xCA by CharToken(name = "xCA", text = Char(0xCA) /* <control-CA> */)
        private val xCB by CharToken(name = "xCB", text = Char(0xCB) /* <control-CB> */)
        private val xCC by CharToken(name = "xCC", text = Char(0xCC) /* <control-CC> */)
        private val xCD by CharToken(name = "xCD", text = Char(0xCD) /* <control-CD> */)
        private val xCE by CharToken(name = "xCE", text = Char(0xCE) /* <control-CE> */)
        private val xCF by CharToken(name = "xCF", text = Char(0xCF) /* <control-CF> */)
        private val xD0 by CharToken(name = "xD0", text = Char(0xD0) /* <control-D0> */)
        private val xD1 by CharToken(name = "xD1", text = Char(0xD1) /* <control-D1> */)
        private val xD2 by CharToken(name = "xD2", text = Char(0xD2) /* <control-D2> */)
        private val xD3 by CharToken(name = "xD3", text = Char(0xD3) /* <control-D3> */)
        private val xD4 by CharToken(name = "xD4", text = Char(0xD4) /* <control-D4> */)
        private val xD5 by CharToken(name = "xD5", text = Char(0xD5) /* <control-D5> */)
        private val xD6 by CharToken(name = "xD6", text = Char(0xD6) /* <control-D6> */)
        private val xD7 by CharToken(name = "xD7", text = Char(0xD7) /* <control-D7> */)
        private val xD8 by CharToken(name = "xD8", text = Char(0xD8) /* <control-D8> */)
        private val xD9 by CharToken(name = "xD9", text = Char(0xD9) /* <control-D9> */)
        private val xDA by CharToken(name = "xDA", text = Char(0xDA) /* <control-DA> */)
        private val xDB by CharToken(name = "xDB", text = Char(0xDB) /* <control-DB> */)
        private val xDC by CharToken(name = "xDC", text = Char(0xDC) /* <control-DC> */)
        private val xDD by CharToken(name = "xDD", text = Char(0xDD) /* <control-DD> */)
        private val xDE by CharToken(name = "xDE", text = Char(0xDE) /* <control-DE> */)
        private val xDF by CharToken(name = "xDF", text = Char(0xDF) /* <control-DF> */)
        private val xE0 by CharToken(name = "xE0", text = Char(0xE0) /* <control-E0> */)
        private val xE1 by CharToken(name = "xE1", text = Char(0xE1) /* <control-E1> */)
        private val xE2 by CharToken(name = "xE2", text = Char(0xE2) /* <control-E2> */)
        private val xE3 by CharToken(name = "xE3", text = Char(0xE3) /* <control-E3> */)
        private val xE4 by CharToken(name = "xE4", text = Char(0xE4) /* <control-E4> */)
        private val xE5 by CharToken(name = "xE5", text = Char(0xE5) /* <control-E5> */)
        private val xE6 by CharToken(name = "xE6", text = Char(0xE6) /* <control-E6> */)
        private val xE7 by CharToken(name = "xE7", text = Char(0xE7) /* <control-E7> */)
        private val xE8 by CharToken(name = "xE8", text = Char(0xE8) /* <control-E8> */)
        private val xE9 by CharToken(name = "xE9", text = Char(0xE9) /* <control-E9> */)
        private val xEA by CharToken(name = "xEA", text = Char(0xEA) /* <control-EA> */)
        private val xEB by CharToken(name = "xEB", text = Char(0xEB) /* <control-EB> */)
        private val xEC by CharToken(name = "xEC", text = Char(0xEC) /* <control-EC> */)
        private val xED by CharToken(name = "xED", text = Char(0xED) /* <control-ED> */)
        private val xEE by CharToken(name = "xEE", text = Char(0xEE) /* <control-EE> */)
        private val xEF by CharToken(name = "xEF", text = Char(0xEF) /* <control-EF> */)
        private val xF0 by CharToken(name = "xF0", text = Char(0xF0) /* <control-F0> */)
        private val xF1 by CharToken(name = "xF1", text = Char(0xF1) /* <control-F1> */)
        private val xF2 by CharToken(name = "xF2", text = Char(0xF2) /* <control-F2> */)
        private val xF3 by CharToken(name = "xF3", text = Char(0xF3) /* <control-F3> */)
        private val xF4 by CharToken(name = "xF4", text = Char(0xF4) /* <control-F4> */)
        private val xF5 by CharToken(name = "xF5", text = Char(0xF5) /* <control-F5> */)
        private val xF6 by CharToken(name = "xF6", text = Char(0xF6) /* <control-F6> */)
        private val xF7 by CharToken(name = "xF7", text = Char(0xF7) /* <control-F7> */)
        private val xF8 by CharToken(name = "xF8", text = Char(0xF8) /* <control-F8> */)
        private val xF9 by CharToken(name = "xF9", text = Char(0xF9) /* <control-F9> */)
        private val xFA by CharToken(name = "xFA", text = Char(0xFA) /* <control-FA> */)
        private val xFB by CharToken(name = "xFB", text = Char(0xFB) /* <control-FB> */)
        private val xFC by CharToken(name = "xFC", text = Char(0xFC) /* <control-FC> */)
        private val xFD by CharToken(name = "xFD", text = Char(0xFD) /* <control-FD> */)
        private val xFE by CharToken(name = "xFE", text = Char(0xFE) /* <control-FE> */)
        private val xFF by CharToken(name = "xFF", text = Char(0xFF) /* <control-FF> */)


        private val ALPHA by x41 or x42 or x43 or x44 or x45 or x46 or x47 or x48 or x49 or x4A or x4B or x4C or x4D or x4E or x4F or x50 or x51 or x52 or x53 or x54 or x55 or x56 or x57 or x58 or x59 or x5A or x61 or x62 or x63 or x64 or x65 or x66 or x67 or x68 or x69 or x6A or x6B or x6C or x6D or x6E or x6F or x70 or x71 or x72 or x73 or x74 or x75 or x76 or x77 or x78 or x79 or x7A map { it.text }
        private val BIT by x30 or x31 map { it.text }
        private val CHAR by x01 or x02 or x03 or x04 or x05 or x06 or x07 or x08 or x09 or x0A or x0B or x0C or x0D or x0E or x0F or x10 or x11 or x12 or x13 or x14 or x15 or x16 or x17 or x18 or x19 or x1A or x1B or x1C or x1D or x1E or x1F or x20 or x21 or x22 or x23 or x24 or x25 or x26 or x27 or x28 or x29 or x2A or x2B or x2C or x2D or x2E or x2F or x30 or x31 or x32 or x33 or x34 or x35 or x36 or x37 or x38 or x39 or x3A or x3B or x3C or x3D or x3E or x3F or x40 or x41 or x42 or x43 or x44 or x45 or x46 or x47 or x48 or x49 or x4A or x4B or x4C or x4D or x4E or x4F or x50 or x51 or x52 or x53 or x54 or x55 or x56 or x57 or x58 or x59 or x5A or x5B or x5C or x5D or x5E or x5F or x60 or x61 or x62 or x63 or x64 or x65 or x66 or x67 or x68 or x69 or x6A or x6B or x6C or x6D or x6E or x6F or x70 or x71 or x72 or x73 or x74 or x75 or x76 or x77 or x78 or x79 or x7A or x7B or x7C or x7D or x7E or x7F map { it.text }
        private val CR by x0D map { it.text }
        private val CRLF by x0D and x0A map { (cr, lf) -> cr.text + lf.text }
        private val CTL by x00 or x01 or x02 or x03 or x04 or x05 or x06 or x07 or x08 or x09 or x0A or x0B or x0C or x0D or x0E or x0F or x10 or x11 or x12 or x13 or x14 or x15 or x16 or x17 or x18 or x19 or x1A or x1B or x1C or x1D or x1E or x1F or x7F map { it.text }
        private val DIGIT by x30 or x31 or x32 or x33 or x34 or x35 or x36 or x37 or x38 or x39 map { it.text }
        private val DQUOTE by x22 map { it.text }
        private val HEXDIG by DIGIT or (x41 or x42 or x43 or x44 or x45 or x46 or x61 or x62 or x63 or x64 or x65 or x66  map { it.text })
        private val HTAB by x09 map { it.text }
        private val LF by x0A map { it.text }
        private val SP by x20 map { it.text }
        private val WSP by SP or HTAB
        private val LWSP by zeroOrMore((CRLF and WSP) or WSP)
        private val OCTET by x00 or x01 or x02 or x03 or x04 or x05 or x06 or x07 or x08 or x09 or x0A or x0B or x0C or x0D or x0E or x0F or x10 or x11 or x12 or x13 or x14 or x15 or x16 or x17 or x18 or x19 or x1A or x1B or x1C or x1D or x1E or x1F or x20 or x21 or x22 or x23 or x24 or x25 or x26 or x27 or x28 or x29 or x2A or x2B or x2C or x2D or x2E or x2F or x30 or x31 or x32 or x33 or x34 or x35 or x36 or x37 or x38 or x39 or x3A or x3B or x3C or x3D or x3E or x3F or x40 or x41 or x42 or x43 or x44 or x45 or x46 or x47 or x48 or x49 or x4A or x4B or x4C or x4D or x4E or x4F or x50 or x51 or x52 or x53 or x54 or x55 or x56 or x57 or x58 or x59 or x5A or x5B or x5C or x5D or x5E or x5F or x60 or x61 or x62 or x63 or x64 or x65 or x66 or x67 or x68 or x69 or x6A or x6B or x6C or x6D or x6E or x6F or x70 or x71 or x72 or x73 or x74 or x75 or x76 or x77 or x78 or x79 or x7A or x7B or x7C or x7D or x7E or x7F or x80 or x81 or x82 or x83 or x84 or x85 or x86 or x87 or x88 or x89 or x8A or x8B or x8C or x8D or x8E or x8F or x90 or x91 or x92 or x93 or x94 or x95 or x96 or x97 or x98 or x99 or x9A or x9B or x9C or x9D or x9E or x9F or xA0 or xA1 or xA2 or xA3 or xA4 or xA5 or xA6 or xA7 or xA8 or xA9 or xAA or xAB or xAC or xAD or xAE or xAF or xB0 or xB1 or xB2 or xB3 or xB4 or xB5 or xB6 or xB7 or xB8 or xB9 or xBA or xBB or xBC or xBD or xBE or xBF or xC0 or xC1 or xC2 or xC3 or xC4 or xC5 or xC6 or xC7 or xC8 or xC9 or xCA or xCB or xCC or xCD or xCE or xCF or xD0 or xD1 or xD2 or xD3 or xD4 or xD5 or xD6 or xD7 or xD8 or xD9 or xDA or xDB or xDC or xDD or xDE or xDF or xE0 or xE1 or xE2 or xE3 or xE4 or xE5 or xE6 or xE7 or xE8 or xE9 or xEA or xEB or xEC or xED or xEE or xEF or xF0 or xF1 or xF2 or xF3 or xF4 or xF5 or xF6 or xF7 or xF8 or xF9 or xFA or xFB or xFC or xFD or xFE or xFF map { it.text }
        private val VCHAR by x21 or x22 or x23 or x24 or x25 or x26 or x27 or x28 or x29 or x2A or x2B or x2C or x2D or x2E or x2F or x30 or x31 or x32 or x33 or x34 or x35 or x36 or x37 or x38 or x39 or x3A or x3B or x3C or x3D or x3E or x3F or x40 or x41 or x42 or x43 or x44 or x45 or x46 or x47 or x48 or x49 or x4A or x4B or x4C or x4D or x4E or x4F or x50 or x51 or x52 or x53 or x54 or x55 or x56 or x57 or x58 or x59 or x5A or x5B or x5C or x5D or x5E or x5F or x60 or x61 or x62 or x63 or x64 or x65 or x66 or x67 or x68 or x69 or x6A or x6B or x6C or x6D or x6E or x6F or x70 or x71 or x72 or x73 or x74 or x75 or x76 or x77 or x78 or x79 or x7A or x7B or x7C or x7D or x7E map { it.text }

        private val exclamation by x21 map { it.text }
        private val dollar by x24 map { it.text }
        private val ampersand by x26 map { it.text }
        private val singleQuote by x27 map { it.text }
        private val leftParenthesis by x28 map { it.text }
        private val rightParenthesis by x29 map { it.text }
        private val asterisk by x2A map { it.text }
        private val plus by x2B map { it.text }
        private val comma by x2C map { it.text }
        private val colon by x3A map { it.text }
        private val semicolon by x3B map { it.text }
        private val equal by x3D map { it.text }
        private val dot by x2E map { it.text }
        private val at by x40 map { it.text }
        private val minus by x2D map { it.text }
        private val doubleColon by 2 timesAsText colon
        private val question by x3F map { it.text }
        private val hash by x23 map { it.text }
        private val slash by x2F map { it.text }
        private val leftSquareBracket by x5B map { it.text }
        private val rightSquareBracket by x5D map { it.text }
        private val underscore by x5F map { it.text }
        private val tilde by x7E map { it.text }
        private val percent by x25 map { it.text }
        private val lt by x3C map { it.text }
        private val gt by x3E map { it.text }

        private val zero by x30 map { it.text }
        private val one by x31 map { it.text }
        private val two by x32 map { it.text }
        private val three by x33 map { it.text }
        private val four by x34 map { it.text }
        private val five by x35 map { it.text }
        private val six by x36 map { it.text }
        private val seven by x37 map { it.text }
        private val eight by x38 map { it.text }
        private val nine by x39 map { it.text }
        private val a by x61 map { it.text }
        private val b by x62 map { it.text }
        private val c by x63 map { it.text }
        private val d by x64 map { it.text }
        private val e by x65 map { it.text }
        private val f by x66 map { it.text }
        private val g by x67 map { it.text }
        private val h by x68 map { it.text }
        private val i by x69 map { it.text }
        private val j by x6A map { it.text }
        private val k by x6B map { it.text }
        private val l by x6C map { it.text }
        private val m by x6D map { it.text }
        private val n by x6E map { it.text }
        private val o by x6F map { it.text }
        private val p by x70 map { it.text }
        private val q by x71 map { it.text }
        private val r by x72 map { it.text }
        private val s by x73 map { it.text }
        private val t by x74 map { it.text }
        private val u by x75 map { it.text }
        private val v by x76 map { it.text }
        private val w by x77 map { it.text }
        private val x by x78 map { it.text }
        private val y by x79 map { it.text }
        private val z by x7A map { it.text }

        // Primitives
        private val POS_DIGIT by one or two or three or four or five or six or seven or eight or nine

        // alpha-numeric =          ALPHA / DIGIT
        private val ALPHA_NUMERIC by ALPHA or DIGIT

        // decimal-uchar =      DIGIT
        //                      / POS-DIGIT DIGIT
        //                      / ("1" 2*(DIGIT))
        //                      / ("2" ("0"/"1"/"2"/"3"/"4") DIGIT)
        //                      / ("2" "5" ("0"/"1"/"2"/"3"/"4"/"5"))
        private val decimalUchar: Parser<String> by
        (two and five and (zero or one or two or three or four or five) map { (a, b, c) -> a + b + c }) or
                ((two and (zero or one or two or three or four) and DIGIT) map { (a, b, c) -> a + b + c }) or
                ((one and (2 times DIGIT)) map { (a, b) -> a + b.text }) or
                ((POS_DIGIT and DIGIT) map { (a, b) -> a + b }) or
                DIGIT

        // hex4    =            1*4HEXDIG
        private val hex4: Parser<String> by 1..4 timesAsText HEXDIG

        // hexseq  =             hex4 *( ":" hex4)
        internal val hexseq by hex4 and zeroOrMoreAsText(colon and hex4 map { it.text2 }) map { it.text2 }

        // hexpart =             hexseq / hexseq "::" [ hexseq ] /
        //                       "::" [ hexseq ]
        private val hexpart by
                (hexseq and doubleColon and (0..1 timesAsText  hexseq) map { it.text3 }) or
                (doubleColon and (0..1 timesAsText  hexseq) map { it.text2 }) or
                hexseq

        // ==================== URI ABNF ====================
        // sub-delims    = "!" / "$" / "&" / "'" / "(" / ")"
        //                 / "*" / "+" / "," / ";" / "="
        private val subDelims: Parser<String> by exclamation or dollar or ampersand or singleQuote or leftParenthesis or rightParenthesis or asterisk or plus or comma or semicolon or equal

        // gen-delims    = ":" / "/" / "?" / "#" / "[" / "]" / "@"
        private val genDelims: Parser<String> by colon or slash or question or hash or leftSquareBracket or rightSquareBracket or at

        // reserved      = gen-delims / sub-delims
        private val reserved: Parser<String> by genDelims or subDelims

        // unreserved    = ALPHA / DIGIT / "-" / "." / "_" / "~"
        private val unreserved: Parser<String> by ALPHA or DIGIT or minus or dot or underscore or tilde

        // pct-encoded   = "%" HEXDIG HEXDIG
        private val pctEncoded: Parser<String> by percent * HEXDIG * HEXDIG map { it.text3 }

        // pchar         = unreserved / pct-encoded / sub-delims / ":" / "@"
        private val pchar: Parser<String> by unreserved or pctEncoded or subDelims or colon or at

        // query         = *( pchar / "/" / "?" )
        private val query: Parser<String> by zeroOrMore(pchar or slash or question) map { it.text }

        // fragment      = *( pchar / "/" / "?" )
        private val fragment: Parser<String> by zeroOrMore(pchar or slash or question) map { it.text }

        // reg-name      = *( unreserved / pct-encoded / sub-delims )
        private val regName: Parser<String> by zeroOrMore(unreserved or pctEncoded or subDelims) map { it.text }

        // segment-nz-nc = 1*( unreserved / pct-encoded / sub-delims / "@" )
        //                 ; non-zero-length segment without any colon ":"
        private val segmentNzNc: Parser<String> by 1..Int.MAX_VALUE timesAsText (unreserved or pctEncoded or subDelims or at)

        // segment-nz    = 1*pchar
        private val segmentNz: Parser<String> by 1..Int.MAX_VALUE timesAsText pchar

        // segment       = *pchar
        private val segment: Parser<String> by zeroOrMore(pchar) map { it.text }

        // path-empty    = 0<pchar>
        private val pathEmpty: Parser<String> by 0 timesAsText pchar

        // path-rootless = segment-nz *( "/" segment )
        private val pathRootless: Parser<String> by segmentNz * (0..Int.MAX_VALUE timesAsText (slash * segment map { it.text2} )) map { it.text2 }

        // path-noscheme = segment-nz-nc *( "/" segment )
        private val pathNoScheme: Parser<String> by segmentNzNc * (0..Int.MAX_VALUE timesAsText (slash * segment map { it.text2} )) map { it.text2 }

        // path-absolute = "/" [ segment-nz *( "/" segment ) ]
        private val optionalPathRootless: Parser<String> by optional(pathRootless) map { it ?: "" }
        private val pathAbsolute: Parser<String> by slash * optionalPathRootless map { it.text2 }

        // path-abempty  = *( "/" segment )
        private val pathAbEmpty: Parser<String> by zeroOrMoreAsText(slash * segment map { it.text2 })

        // path          = path-abempty    ; begins with "/" or is empty
        //                 / path-absolute   ; begins with "/" but not "//"
        //                 / path-noscheme   ; begins with a non-colon segment
        //                 / path-rootless   ; begins with a segment
        //                 / path-empty      ; zero characters
        private val path: Parser<String> by pathAbEmpty or pathAbsolute or pathNoScheme or pathRootless or pathEmpty

        // dec-octet     = DIGIT                 ; 0-9
        //                 / %x31-39 DIGIT         ; 10-99
        //                 / "1" 2DIGIT            ; 100-199
        //                 / "2" %x30-34 DIGIT     ; 200-249
        //                 / "25" %x30-35          ; 250-255
        private val decOctet: Parser<String> by decimalUchar
        // IPv4address   = dec-octet "." dec-octet "." dec-octet "." dec-octet
        private val IPv4Address: Parser<String> by decOctet * dot * decOctet * dot * decOctet * dot * decOctet map { it.text7 }

        // h16           = 1*4HEXDIG
        internal val h16: Parser<String> by 1..4 timesAsText HEXDIG

        // ls32          = ( h16 ":" h16 ) / IPv4address
        internal val ls32: Parser<String> by h16 * colon * h16 map { it.text3 } or IPv4Address

        // scheme        = ALPHA *( ALPHA / DIGIT / "+" / "-" / "." )
        internal val schema: Parser<String> by ALPHA and zeroOrMore(ALPHA or DIGIT or plus or minus or dot) map { (a, b) -> a + b.text }

        // userinfo      = *( unreserved / pct-encoded / sub-delims / ":" )
        internal val userInfo: Parser<String> by zeroOrMore(unreserved or pctEncoded or subDelims or colon) map { it.text }

        // IPv6address   =                            6( h16 ":" ) ls32
        //                 /                       "::" 5( h16 ":" ) ls32
        //                 / [               h16 ] "::" 4( h16 ":" ) ls32
        //                 / [ *1( h16 ":" ) h16 ] "::" 3( h16 ":" ) ls32
        //                 / [ *2( h16 ":" ) h16 ] "::" 2( h16 ":" ) ls32
        //                 / [ *3( h16 ":" ) h16 ] "::"    h16 ":"   ls32
        //                 / [ *4( h16 ":" ) h16 ] "::"              ls32
        //                 / [ *5( h16 ":" ) h16 ] "::"              h16
        //                 / [ *6( h16 ":" ) h16 ] "::"
//        private val h16AndColon: Parser<String> by h16 * colon map { it.text2 }
//        private val optional0h16: Parser<String> by optional(h16) map { it ?: "" }
//        private val optional1h16: Parser<String> by optional((0..1 timesAsText h16AndColon) * h16 map { it.text2 }) map { it ?: "" }
//        private val optional2h16: Parser<String> by optional((0..2 timesAsText h16AndColon) * h16 map { it.text2 }) map { it ?: "" }
//        private val optional3h16: Parser<String> by optional((0..3 timesAsText h16AndColon) * h16 map { it.text2 }) map { it ?: "" }
//        private val optional4h16: Parser<String> by optional((0..4 timesAsText h16AndColon) * h16 map { it.text2 }) map { it ?: "" }
//        private val optional5h16: Parser<String> by optional((0..5 timesAsText h16AndColon) * h16 map { it.text2 }) map { it ?: "" }
//        private val optional6h16: Parser<String> by optional((0..6 timesAsText h16AndColon) * h16 map { it.text2 }) map { it ?: "" }
//        internal val IPv6Address: Parser<String> by
//                ((6 timesAsText h16AndColon) * ls32 map { it.text2 }) or
//                (doubleColon * (5 timesAsText h16AndColon) * ls32 map { it.text3 }) or
//                (optional0h16 * doubleColon * (4 timesAsText h16AndColon) * ls32 map { it.text4 }) or
//                (optional1h16 * doubleColon * (3 timesAsText h16AndColon) * ls32 map { it.text4 }) or
//                (optional2h16 * doubleColon * (2 timesAsText h16AndColon) * ls32 map { it.text4 }) or
//                (optional3h16 * doubleColon * h16AndColon * ls32 map { it.text4 }) or
//                (optional4h16 * doubleColon * ls32 map { it.text3 }) or
//                (optional5h16 * doubleColon * h16 map { it.text3 }) or
//                (optional6h16 * doubleColon map { it.text2 })

//         IPv6address    =  hexpart [ ":" IPv4address ]
        internal val IPv6Address: Parser<String> by hexpart //* optional(colon * IPv4Address) map { it.text2 }

        // IPvFuture     = "v" 1*HEXDIG "." 1*( unreserved / sub-delims / ":" )
        internal val IPvFuture: Parser<String> by v * (1..Int.MAX_VALUE timesAsText HEXDIG) * dot * (1..Int.MAX_VALUE timesAsText (unreserved or subDelims or colon)) map { it.text4 }


        // IP-literal    = "[" ( IPv6address / IPvFuture  ) "]"
        internal val IPLiteral: Parser<String> by leftSquareBracket * (IPv6Address or IPvFuture) * rightSquareBracket map { it.text3 }

        // host          = IP-literal / IPv4address / reg-name
        internal val host: Parser<String> by IPLiteral or IPv4Address or regName

        // port          = *DIGIT
        internal val uriPort: Parser<String> by zeroOrMore(DIGIT) map { it.text }

        // authority     = [ userinfo "@" ] host [ ":" port ]
        private val optionalUserInfo: Parser<String> by optional(userInfo * at map { it.text2 }) map { it ?: "" }
        private val optionalPort: Parser<String> by optional(colon * uriPort map { it.text2 }) map { it ?: "" }
        internal val authority by optionalUserInfo * host * optionalPort map { it.text3 }

        // hier-part     = "//" authority path-abempty
        //                 / path-absolute
        //                 / path-rootless
        //                 / path-empty
        private val doubleSlash by 2 timesAsText slash
        internal val hierPart: Parser<String> by
                (doubleSlash * authority * pathAbEmpty map { it.text3 }) or
                pathAbsolute or
                pathRootless or
                pathEmpty

        // URI           = scheme ":" hier-part [ "?" query ] [ "#" fragment ]
        private val optionalQuestionAndQuery: Parser<String> by optional(question * query map {it.text2}) map { it ?: "" }
        private val optionalHashAndFragment: Parser<String> by optional(hash * fragment map {it.text2}) map { it ?: "" }
        internal val URI: Parser<String> by schema * colon * hierPart * optionalQuestionAndQuery * optionalHashAndFragment map { it.text5 }

        // ==================== END OF URI ABNF ====================

        // Data types
        // integer = POS-DIGIT *DIGIT
        private val integer by POS_DIGIT and zeroOrMore(DIGIT) map { (a, b) -> a + b.text }

        // email-safe =         %x01-09 / %x0B-0C / %x0E-27 / %x2A-3B / %x3D / %x3F-FF
        //                      ; any byte except NUL, CR, LF, or the quoting characters ()<>
        private val emailSafe by x01 or x02 or x03 or x04 or x05 or x06 or x07 or x08 or x09 or x0B or x0C or x0E or x0F or x10 or x11 or x12 or x13 or x14 or x15 or x16 or x17 or x18 or x19 or x1A or x1B or x1C or x1D or x1E or x1F or x20 or x21 or x22 or x23 or x24 or x25 or x26 or x27 or x2A or x2B or x2C or x2D or x2E or x2F or x30 or x31 or x32 or x33 or x34 or x35 or x36 or x37 or x38 or x39 or x3A or x3B or x3D or x3F or x40 or x41 or x42 or x43 or x44 or x45 or x46 or x47 or x48 or x49 or x4A or x4B or x4C or x4D or x4E or x4F or x50 or x51 or x52 or x53 or x54 or x55 or x56 or x57 or x58 or x59 or x5A or x5B or x5C or x5D or x5E or x5F or x60 or x61 or x62 or x63 or x64 or x65 or x66 or x67 or x68 or x69 or x6A or x6B or x6C or x6D or x6E or x6F or x70 or x71 or x72 or x73 or x74 or x75 or x76 or x77 or x78 or x79 or x7A or x7B or x7C or x7D or x7E or x7F or x80 or x81 or x82 or x83 or x84 or x85 or x86 or x87 or x88 or x89 or x8A or x8B or x8C or x8D or x8E or x8F or x90 or x91 or x92 or x93 or x94 or x95 or x96 or x97 or x98 or x99 or x9A or x9B or x9C or x9D or x9E or x9F or xA0 or xA1 or xA2 or xA3 or xA4 or xA5 or xA6 or xA7 or xA8 or xA9 or xAA or xAB or xAC or xAD or xAE or xAF or xB0 or xB1 or xB2 or xB3 or xB4 or xB5 or xB6 or xB7 or xB8 or xB9 or xBA or xBB or xBC or xBD or xBE or xBF or xC0 or xC1 or xC2 or xC3 or xC4 or xC5 or xC6 or xC7 or xC8 or xC9 or xCA or xCB or xCC or xCD or xCE or xCF or xD0 or xD1 or xD2 or xD3 or xD4 or xD5 or xD6 or xD7 or xD8 or xD9 or xDA or xDB or xDC or xDD or xDE or xDF or xE0 or xE1 or xE2 or xE3 or xE4 or xE5 or xE6 or xE7 or xE8 or xE9 or xEA or xEB or xEC or xED or xEE or xEF or xF0 or xF1 or xF2 or xF3 or xF4 or xF5 or xF6 or xF7 or xF8 or xF9 or xFA or xFB or xFC or xFD or xFE or xFF map { it.text }

        // token-char =             %x21 / %x23-27 / %x2A-2B / %x2D-2E / %x30-39 / %x41-5A / %x5E-7E
        private val tokenChar by x21 or x23 or x24 or x25 or x26 or x27 or x2A or x2B or x2D or x2E or x30 or x31 or x32 or x33 or x34 or x35 or x36 or x37 or x38 or x39 or x41 or x42 or x43 or x44 or x45 or x46 or x47 or x48 or x49 or x4A or x4B or x4C or x4D or x4E or x4F or x50 or x51 or x52 or x53 or x54 or x55 or x56 or x57 or x58 or x59 or x5A or x5E or x5F or x60 or x61 or x62 or x63 or x64 or x65 or x66 or x67 or x68 or x69 or x6A or x6B or x6C or x6D or x6E or x6F or x70 or x71 or x72 or x73 or x74 or x75 or x76 or x77 or x78 or x79 or x7A or x7B or x7C or x7D or x7E map { it.text }

        // token =              1*(token-char)
        private val token by oneOrMore(tokenChar) map { it.text }

        // non-ws-string =      1*(VCHAR/%x80-FF)
        //                      ; string of visible characters
        private val nonWsString by oneOrMore(VCHAR or (x80 or x81 or x82 or x83 or x84 or x85 or x86 or x87 or x88 or x89 or x8A or x8B or x8C or x8D or x8E or x8F or x90 or x91 or x92 or x93 or x94 or x95 or x96 or x97 or x98 or x99 or x9A or x9B or x9C or x9D or x9E or x9F or xA0 or xA1 or xA2 or xA3 or xA4 or xA5 or xA6 or xA7 or xA8 or xA9 or xAA or xAB or xAC or xAD or xAE or xAF or xB0 or xB1 or xB2 or xB3 or xB4 or xB5 or xB6 or xB7 or xB8 or xB9 or xBA or xBB or xBC or xBD or xBE or xBF or xC0 or xC1 or xC2 or xC3 or xC4 or xC5 or xC6 or xC7 or xC8 or xC9 or xCA or xCB or xCC or xCD or xCE or xCF or xD0 or xD1 or xD2 or xD3 or xD4 or xD5 or xD6 or xD7 or xD8 or xD9 or xDA or xDB or xDC or xDD or xDE or xDF or xE0 or xE1 or xE2 or xE3 or xE4 or xE5 or xE6 or xE7 or xE8 or xE9 or xEA or xEB or xEC or xED or xEE or xEF or xF0 or xF1 or xF2 or xF3 or xF4 or xF5 or xF6 or xF7 or xF8 or xF9 or xFA or xFB or xFC or xFD or xFE or xFF map { it.text })) map { it.text }

        // byte-string =        1*(%x01-09/%x0B-0C/%x0E-FF)
        //                      ; any byte except NUL, CR, or LF
        private val byteString by oneOrMore(x01 or x02 or x03 or x04 or x05 or x06 or x07 or x08 or x09 or x0B or x0C or x0E or x0F or x10 or x11 or x12 or x13 or x14 or x15 or x16 or x17 or x18 or x19 or x1A or x1B or x1C or x1D or x1E or x1F or x20 or x21 or x22 or x23 or x24 or x25 or x26 or x27 or x28 or x29 or x2A or x2B or x2C or x2D or x2E or x2F or x30 or x31 or x32 or x33 or x34 or x35 or x36 or x37 or x38 or x39 or x3A or x3B or x3C or x3D or x3E or x3F or x40 or x41 or x42 or x43 or x44 or x45 or x46 or x47 or x48 or x49 or x4A or x4B or x4C or x4D or x4E or x4F or x50 or x51 or x52 or x53 or x54 or x55 or x56 or x57 or x58 or x59 or x5A or x5B or x5C or x5D or x5E or x5F or x60 or x61 or x62 or x63 or x64 or x65 or x66 or x67 or x68 or x69 or x6A or x6B or x6C or x6D or x6E or x6F or x70 or x71 or x72 or x73 or x74 or x75 or x76 or x77 or x78 or x79 or x7A or x7B or x7C or x7D or x7E or x7F or x80 or x81 or x82 or x83 or x84 or x85 or x86 or x87 or x88 or x89 or x8A or x8B or x8C or x8D or x8E or x8F or x90 or x91 or x92 or x93 or x94 or x95 or x96 or x97 or x98 or x99 or x9A or x9B or x9C or x9D or x9E or x9F or xA0 or xA1 or xA2 or xA3 or xA4 or xA5 or xA6 or xA7 or xA8 or xA9 or xAA or xAB or xAC or xAD or xAE or xAF or xB0 or xB1 or xB2 or xB3 or xB4 or xB5 or xB6 or xB7 or xB8 or xB9 or xBA or xBB or xBC or xBD or xBE or xBF or xC0 or xC1 or xC2 or xC3 or xC4 or xC5 or xC6 or xC7 or xC8 or xC9 or xCA or xCB or xCC or xCD or xCE or xCF or xD0 or xD1 or xD2 or xD3 or xD4 or xD5 or xD6 or xD7 or xD8 or xD9 or xDA or xDB or xDC or xDD or xDE or xDF or xE0 or xE1 or xE2 or xE3 or xE4 or xE5 or xE6 or xE7 or xE8 or xE9 or xEA or xEB or xEC or xED or xEE or xEF or xF0 or xF1 or xF2 or xF3 or xF4 or xF5 or xF6 or xF7 or xF8 or xF9 or xFA or xFB or xFC or xFD or xFE or xFF) map { chars -> chars.textOfTokens }

        // text =               byte-string
        //                      ; default is to interpret this as UTF8 text.
        //                      ; ISO 8859-1 requires "a=charset:ISO-8859-1"
        //                      ; session-level attribute to be used
        private val text by byteString

        // Generic for other address families

        // extn-addr =          non-ws-string
        private val extnAddr by nonWsString

        // b1 =                  decimal-uchar
        //                       ; less than "224"
        internal val b1 by decimalUchar

        // IP4-address =         b1 3("." decimal-uchar)
        internal val ip4Address by b1 and 3.times(dot and decimalUchar) map { (a, b) -> a + b.text2}

        // IP6-address =         hexpart [ ":" IP4-address ]
        internal val ip6Address by hexpart and (0..1 times (colon and ip4Address)) map { (a, b) -> a + b.text2 }

        // generic sub-rules: addressing
        // FQDN =                4*(alpha-numeric / "-" / ".")
        //                       ; fully qualified domain name as specified
        //                       ; in RFC 1035 (and updates)
        internal val FQDN by 4..Int.MAX_VALUE timesAsText (ALPHA_NUMERIC or minus or dot)

        // ttl =                 (POS-DIGIT *2DIGIT) / "0"
        internal val ttl by ((POS_DIGIT and (0..2 times DIGIT)) map { (a, b) -> a + b.text}) or zero

        // IP6-multicast =       hexpart [ "/" integer ]
        //                       ; IPv6 address starting with FF
        internal val ip6Multicast by (hexpart and (0..1 times (slash and integer))) map { (a, b) -> a + b.text2 }

        private val `22` by two and two map { (a, b) -> a + b }
        private val `23` by two and three map { (a, b) -> a + b }
        // m1 =                  ("22" ("4"/"5"/"6"/"7"/"8"/"9")) / ("23" DIGIT )
        internal val m1 by (`22` and (four or five or six or seven or eight or nine)) or (`23` and DIGIT) map { (a, b) -> a + b }

        // IP4-multicast =       m1 3( "." decimal-uchar ) "/" ttl [ "/" integer ]
        //                       ; IPv4 multicast addresses may be in the
        //                       ; range 224.0.0.0 to 239.255.255.255
        internal val ip4Multicast by m1 and (3 times (dot and decimalUchar)) and slash and ttl and ((0..1 times (slash and integer))) map { (a, b, c, d, e) -> a + b.text2 + c + d + e.text2 }

        // multicast-address =   IP4-multicast / IP6-multicast / FQDN / extn-addr
        internal val multicastAddress by ip4Multicast orNext  ip6Multicast orNext  FQDN orNext  extnAddr

        // unicast-address =     IP4-address / IP6-address / FQDN / extn-addr
        internal val unicastAddress by ip4Address orNext  ip6Address orNext  FQDN  orNext extnAddr

        // ; sub-rules of 'm='

        // media =               token
        //                       ;typically "audio", "video", "text", or
        //                       ;"application"
        internal val media by token

        // fmt =                 token
        //                       ;typically an RTP payload type for audio
        //                       ;and video media
        internal val fmt by token

        // proto  =              token *("/" token)
        //                       ;typically "RTP/AVP" or "udp"
        internal val proto by token and zeroOrMore(slash and token) map { (a, b) -> a + b.text2 }

        // port =                1*DIGIT
        internal val port by oneOrMore(DIGIT) map { it.text }
        
        // ; sub-rules of 'a='

        // att-field =           token
        internal val attField by token

        // att-value =           byte-string
        internal val attValue by byteString

        // attribute =           (att-field ":" att-value) / att-field
        internal val attribute by (attField and colon and attValue) map { it.text3 } or attField

        // ; sub-rules of 'u='
        // uri =                 URI-reference
        //                       ; see RFC 3986
        private val uri by URI

        // ; sub-rules of 'p='
        //   phone =               ["+"] DIGIT 1*(SP / "-" / DIGIT)
        private val optionalPlus: Parser<String> by optional(plus) map { it ?: "" }
        private val phone by optionalPlus and DIGIT and oneOrMoreAsText(SP or minus or DIGIT) map { it.text3 }

        //   phone-number =        phone *SP "(" 1*email-safe ")" /
        //                         1*email-safe "<" phone ">" /
        //                         phone
        private val phoneNumber by
                ((phone and zeroOrMoreAsText(SP) and leftParenthesis and oneOrMoreAsText(emailSafe) and rightParenthesis) map { it.text5 }) or
                (oneOrMoreAsText(emailSafe) and lt and phone and gt map { it.text4 }) or
                phone

        //; sub-rules of 'c='
        //   connection-address =  multicast-address / unicast-address
        private val connectionAddress: Parser<String> by multicastAddress or unicastAddress

        //; sub-rules of 'b='
        //   bwtype =              token
        private val bwtype: Parser<String> by token

        //   bandwidth =           1*DIGIT
        private val bandwidth: Parser<String> by oneOrMore(DIGIT) map { it.text }

        // ; sub-rules of 't='
        // time =                POS-DIGIT 9*DIGIT
        //                         ; Decimal representation of NTP time in
        //                         ; seconds since 1900.  The representation
        //                         ; of NTP time is an unbounded length field
        //                         ; containing at least 10 digits.  Unlike the
        //                         ; 64-bit representation used elsewhere, time
        //                         ; in SDP does not wrap in the year 2036.
        private val time: Parser<String> by POS_DIGIT and (9 timesAsText  DIGIT) map { it.text2 }

        // stop-time =           time / "0"
        private val stopTime: Parser<String> by time or zero

        // start-time =          time / "0"
        private val startTime: Parser<String> by time or zero

        //; sub-rules of 'r=' and 'z='
        // fixed-len-time-unit = %x64 / %x68 / %x6d / %x73
        private val fixedLenTimeUnit: Parser<String> = d or h or m or s

        // typed-time =          1*DIGIT [fixed-len-time-unit]
        private val optionalFixedLineUnit: Parser<String> by optional(fixedLenTimeUnit) map { it ?: "" }
        private val typedTime: Parser<String> by 1..Int.MAX_VALUE timesAsText DIGIT and optionalFixedLineUnit map { it.text2 }

        // ; sub-rules of 'k='
        // base64-char =         ALPHA / DIGIT / "+" / "/"
        internal val base64Char by ALPHA or DIGIT or plus or slash

        // base64-pad  =         2base64-char "==" / 3base64-char "="
        internal val base64Pad by
                        (((2 times base64Char map { it.text }) * equal * equal) map { it.text3 }) or
                        (((3 times base64Char map { it.text }) * equal) map { it.text2 })

        // base64-unit =         4base64-char
        internal val base64Unit by 4 times base64Char map { it.text }

        // base64      =         *base64-unit [base64-pad]
        internal val base64 by (zeroOrMore(base64Unit) map { it.text }) * (( 0..1 times base64Pad) map { it.text}) map { it.text2 }

        // key-type =            %x70 %x72 %x6f %x6d %x70 %x74 /     ; "prompt"
        //                       %x63 %x6c %x65 %x61 %x72 ":" text / ; "clear:"
        //                       %x62 %x61 %x73 %x65 "64:" base64 /  ; "base64:"
        //                       %x75 %x72 %x69 ":" uri              ; "uri:"
        internal val keyType by
                    ((p * r * o * m * p * t) map { it.text6 }) or
                    ((c * l * e * a * r * colon * text) map { it.text7 }) or
                    ((b * a * s * e * six * four * colon * base64)) or
                    ((u * r * i * colon * uri) map { it.text5 })

        private val sdp by schema and
                optional(userInfo) map { (scheme, userInfo) ->
            Sdp(scheme, Authority(userInfo, "", null), null)
        }

        override val rootParser by sdp
    }
}

private val Tuple2<String, String>.text2 get() = t1 + t2
private val Tuple3<String, String, String>.text3 get() = t1 + t2 + t3
private val Tuple4<String, String, String, String>.text4 get() = t1 + t2 + t3 + t4
private val Tuple5<String, String, String, String, String>.text5 get() = t1 + t2 + t3 + t4 + t5
private val Tuple6<String, String, String, String, String, String>.text6 get() = t1 + t2 + t3 + t4 + t5 + t6
private val Tuple7<String, String, String, String, String, String, String>.text7 get() = t1 + t2 + t3 + t4 + t5 + t6 + t7
private val List<String>.text get() = joinToString(separator = "")
private val List<Tuple2<String, String>>.text2 get() = joinToString(separator = "") { it.text2 }
private val List<Tuple3<String, String, String>>.text3 get() = joinToString(separator = "") { it.text3 }
private val List<TokenMatch>.textOfTokens get() = joinToString(separator = "") { it.text }
private infix fun Int.timesAsText(parser: Parser<String>): Parser<String> = this times parser map { it.text }
private infix fun IntRange.timesAsText(parser: Parser<String>): Parser<String> = this times parser map { it.text }
private fun zeroOrMoreAsText(parser: Parser<String>): Parser<String> = zeroOrMore(parser) map { it.text }
private fun oneOrMoreAsText(parser: Parser<String>): Parser<String> = oneOrMore(parser) map { it.text }
// format: on
