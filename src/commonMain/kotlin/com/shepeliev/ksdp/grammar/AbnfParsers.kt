package com.shepeliev.ksdp.grammar

import com.github.h0tk3y.betterParse.combinators.*
import com.github.h0tk3y.betterParse.lexer.TokenMatch
import com.github.h0tk3y.betterParse.parser.Parser
import com.github.h0tk3y.betterParse.utils.*

internal val ALPHA = _x41 or _x42 or _x43 or _x44 or _x45 or _x46 or _x47 or _x48 or _x49 or _x4A or _x4B or _x4C or _x4D or _x4E or _x4F or _x50 or _x51 or _x52 or _x53 or _x54 or _x55 or _x56 or _x57 or _x58 or _x59 or _x5A or _x61 or _x62 or _x63 or _x64 or _x65 or _x66 or _x67 or _x68 or _x69 or _x6A or _x6B or _x6C or _x6D or _x6E or _x6F or _x70 or _x71 or _x72 or _x73 or _x74 or _x75 or _x76 or _x77 or _x78 or _x79 or _x7A map { it.text }
internal val BIT = _x30 or _x31 map { it.text }
internal val CHAR = _x01 or _x02 or _x03 or _x04 or _x05 or _x06 or _x07 or _x08 or _x09 or _x0A or _x0B or _x0C or _x0D or _x0E or _x0F or _x10 or _x11 or _x12 or _x13 or _x14 or _x15 or _x16 or _x17 or _x18 or _x19 or _x1A or _x1B or _x1C or _x1D or _x1E or _x1F or _x20 or _x21 or _x22 or _x23 or _x24 or _x25 or _x26 or _x27 or _x28 or _x29 or _x2A or _x2B or _x2C or _x2D or _x2E or _x2F or _x30 or _x31 or _x32 or _x33 or _x34 or _x35 or _x36 or _x37 or _x38 or _x39 or _x3A or _x3B or _x3C or _x3D or _x3E or _x3F or _x40 or _x41 or _x42 or _x43 or _x44 or _x45 or _x46 or _x47 or _x48 or _x49 or _x4A or _x4B or _x4C or _x4D or _x4E or _x4F or _x50 or _x51 or _x52 or _x53 or _x54 or _x55 or _x56 or _x57 or _x58 or _x59 or _x5A or _x5B or _x5C or _x5D or _x5E or _x5F or _x60 or _x61 or _x62 or _x63 or _x64 or _x65 or _x66 or _x67 or _x68 or _x69 or _x6A or _x6B or _x6C or _x6D or _x6E or _x6F or _x70 or _x71 or _x72 or _x73 or _x74 or _x75 or _x76 or _x77 or _x78 or _x79 or _x7A or _x7B or _x7C or _x7D or _x7E or _x7F map { it.text }
internal val CR = _x0D map { it.text }
internal val CRLF = _x0D and _x0A map { (a, b) -> a.text + b.text }
internal val CTL = _x00 or _x01 or _x02 or _x03 or _x04 or _x05 or _x06 or _x07 or _x08 or _x09 or _x0A or _x0B or _x0C or _x0D or _x0E or _x0F or _x10 or _x11 or _x12 or _x13 or _x14 or _x15 or _x16 or _x17 or _x18 or _x19 or _x1A or _x1B or _x1C or _x1D or _x1E or _x1F or _x7F map { it.text }
internal val DIGIT = _x30 or _x31 or _x32 or _x33 or _x34 or _x35 or _x36 or _x37 or _x38 or _x39 map { it.text }
internal val DQUOTE = _x22 map { it.text }
internal val HEXDIG = DIGIT or (_x41 or _x42 or _x43 or _x44 or _x45 or _x46 or _x61 or _x62 or _x63 or _x64 or _x65 or _x66 map { it.text })
internal val HTAB = _x09 map { it.text }
internal val LF = _x0A map { it.text }
internal val SP = _x20 map { it.text }
internal val WSP = SP or HTAB
internal val LWSP = zeroOrMoreAsText((CRLF and WSP) map { it.text2 } or WSP)
internal val OCTET = _x00 or _x01 or _x02 or _x03 or _x04 or _x05 or _x06 or _x07 or _x08 or _x09 or _x0A or _x0B or _x0C or _x0D or _x0E or _x0F or _x10 or _x11 or _x12 or _x13 or _x14 or _x15 or _x16 or _x17 or _x18 or _x19 or _x1A or _x1B or _x1C or _x1D or _x1E or _x1F or _x20 or _x21 or _x22 or _x23 or _x24 or _x25 or _x26 or _x27 or _x28 or _x29 or _x2A or _x2B or _x2C or _x2D or _x2E or _x2F or _x30 or _x31 or _x32 or _x33 or _x34 or _x35 or _x36 or _x37 or _x38 or _x39 or _x3A or _x3B or _x3C or _x3D or _x3E or _x3F or _x40 or _x41 or _x42 or _x43 or _x44 or _x45 or _x46 or _x47 or _x48 or _x49 or _x4A or _x4B or _x4C or _x4D or _x4E or _x4F or _x50 or _x51 or _x52 or _x53 or _x54 or _x55 or _x56 or _x57 or _x58 or _x59 or _x5A or _x5B or _x5C or _x5D or _x5E or _x5F or _x60 or _x61 or _x62 or _x63 or _x64 or _x65 or _x66 or _x67 or _x68 or _x69 or _x6A or _x6B or _x6C or _x6D or _x6E or _x6F or _x70 or _x71 or _x72 or _x73 or _x74 or _x75 or _x76 or _x77 or _x78 or _x79 or _x7A or _x7B or _x7C or _x7D or _x7E or _x7F or _x80 or _x81 or _x82 or _x83 or _x84 or _x85 or _x86 or _x87 or _x88 or _x89 or _x8A or _x8B or _x8C or _x8D or _x8E or _x8F or _x90 or _x91 or _x92 or _x93 or _x94 or _x95 or _x96 or _x97 or _x98 or _x99 or _x9A or _x9B or _x9C or _x9D or _x9E or _x9F or _xA0 or _xA1 or _xA2 or _xA3 or _xA4 or _xA5 or _xA6 or _xA7 or _xA8 or _xA9 or _xAA or _xAB or _xAC or _xAD or _xAE or _xAF or _xB0 or _xB1 or _xB2 or _xB3 or _xB4 or _xB5 or _xB6 or _xB7 or _xB8 or _xB9 or _xBA or _xBB or _xBC or _xBD or _xBE or _xBF or _xC0 or _xC1 or _xC2 or _xC3 or _xC4 or _xC5 or _xC6 or _xC7 or _xC8 or _xC9 or _xCA or _xCB or _xCC or _xCD or _xCE or _xCF or _xD0 or _xD1 or _xD2 or _xD3 or _xD4 or _xD5 or _xD6 or _xD7 or _xD8 or _xD9 or _xDA or _xDB or _xDC or _xDD or _xDE or _xDF or _xE0 or _xE1 or _xE2 or _xE3 or _xE4 or _xE5 or _xE6 or _xE7 or _xE8 or _xE9 or _xEA or _xEB or _xEC or _xED or _xEE or _xEF or _xF0 or _xF1 or _xF2 or _xF3 or _xF4 or _xF5 or _xF6 or _xF7 or _xF8 or _xF9 or _xFA or _xFB or _xFC or _xFD or _xFE or _xFF map { it.text }
internal val VCHAR = _x21 or _x22 or _x23 or _x24 or _x25 or _x26 or _x27 or _x28 or _x29 or _x2A or _x2B or _x2C or _x2D or _x2E or _x2F or _x30 or _x31 or _x32 or _x33 or _x34 or _x35 or _x36 or _x37 or _x38 or _x39 or _x3A or _x3B or _x3C or _x3D or _x3E or _x3F or _x40 or _x41 or _x42 or _x43 or _x44 or _x45 or _x46 or _x47 or _x48 or _x49 or _x4A or _x4B or _x4C or _x4D or _x4E or _x4F or _x50 or _x51 or _x52 or _x53 or _x54 or _x55 or _x56 or _x57 or _x58 or _x59 or _x5A or _x5B or _x5C or _x5D or _x5E or _x5F or _x60 or _x61 or _x62 or _x63 or _x64 or _x65 or _x66 or _x67 or _x68 or _x69 or _x6A or _x6B or _x6C or _x6D or _x6E or _x6F or _x70 or _x71 or _x72 or _x73 or _x74 or _x75 or _x76 or _x77 or _x78 or _x79 or _x7A or _x7B or _x7C or _x7D or _x7E map { it.text }

internal val exclamation = _x21 map { it.text }
internal val dollar = _x24 map { it.text }
internal val ampersand = _x26 map { it.text }
internal val singleQuote = _x27 map { it.text }
internal val leftParenthesis = _x28 map { it.text }
internal val rightParenthesis = _x29 map { it.text }
internal val asterisk = _x2A map { it.text }
internal val plus = _x2B map { it.text }
internal val comma = _x2C map { it.text }
internal val colon = _x3A map { it.text }
internal val semicolon = _x3B map { it.text }
internal val eq = _x3D map { it.text }
internal val dot = _x2E map { it.text }
internal val at = _x40 map { it.text }
internal val minus = _x2D map { it.text }
internal val doubleColon = 2 timesAsText colon
internal val question = _x3F map { it.text }
internal val hash = _x23 map { it.text }
internal val slash = _x2F map { it.text }
internal val leftSquareBracket = _x5B map { it.text }
internal val rightSquareBracket = _x5D map { it.text }
internal val underscore = _x5F map { it.text }
internal val tilde = _x7E map { it.text }
internal val percent = _x25 map { it.text }
internal val lt = _x3C map { it.text }
internal val gt = _x3E map { it.text }

internal val zero = _x30 map { it.text }
internal val one = _x31 map { it.text }
internal val two = _x32 map { it.text }
internal val three = _x33 map { it.text }
internal val four = _x34 map { it.text }
internal val five = _x35 map { it.text }
internal val six = _x36 map { it.text }
internal val seven = _x37 map { it.text }
internal val eight = _x38 map { it.text }
internal val nine = _x39 map { it.text }
internal val a = _x61 map { it.text }
internal val b = _x62 map { it.text }
internal val c = _x63 map { it.text }
internal val d = _x64 map { it.text }
internal val e = _x65 map { it.text }
internal val f = _x66 map { it.text }
internal val g = _x67 map { it.text }
internal val h = _x68 map { it.text }
internal val i = _x69 map { it.text }
internal val j = _x6A map { it.text }
internal val k = _x6B map { it.text }
internal val l = _x6C map { it.text }
internal val m = _x6D map { it.text }
internal val n = _x6E map { it.text }
internal val o = _x6F map { it.text }
internal val p = _x70 map { it.text }
internal val q = _x71 map { it.text }
internal val r = _x72 map { it.text }
internal val s = _x73 map { it.text }
internal val t = _x74 map { it.text }
internal val u = _x75 map { it.text }
internal val v = _x76 map { it.text }
internal val w = _x77 map { it.text }
internal val x = _x78 map { it.text }
internal val y = _x79 map { it.text }
internal val z = _x7A map { it.text }

// Primitives
internal val POS_DIGIT = one or two or three or four or five or six or seven or eight or nine

// alpha-numeric =          ALPHA / DIGIT
internal val ALPHA_NUMERIC = ALPHA or DIGIT

// non-ws-string =      1*(VCHAR/%x80-FF)
//                      ; string of visible characters
internal val nonWsString = oneOrMoreAsText(VCHAR or (_x80 or _x81 or _x82 or _x83 or _x84 or _x85 or _x86 or _x87 or _x88 or _x89 or _x8A or _x8B or _x8C or _x8D or _x8E or _x8F or _x90 or _x91 or _x92 or _x93 or _x94 or _x95 or _x96 or _x97 or _x98 or _x99 or _x9A or _x9B or _x9C or _x9D or _x9E or _x9F or _xA0 or _xA1 or _xA2 or _xA3 or _xA4 or _xA5 or _xA6 or _xA7 or _xA8 or _xA9 or _xAA or _xAB or _xAC or _xAD or _xAE or _xAF or _xB0 or _xB1 or _xB2 or _xB3 or _xB4 or _xB5 or _xB6 or _xB7 or _xB8 or _xB9 or _xBA or _xBB or _xBC or _xBD or _xBE or _xBF or _xC0 or _xC1 or _xC2 or _xC3 or _xC4 or _xC5 or _xC6 or _xC7 or _xC8 or _xC9 or _xCA or _xCB or _xCC or _xCD or _xCE or _xCF or _xD0 or _xD1 or _xD2 or _xD3 or _xD4 or _xD5 or _xD6 or _xD7 or _xD8 or _xD9 or _xDA or _xDB or _xDC or _xDD or _xDE or _xDF or _xE0 or _xE1 or _xE2 or _xE3 or _xE4 or _xE5 or _xE6 or _xE7 or _xE8 or _xE9 or _xEA or _xEB or _xEC or _xED or _xEE or _xEF or _xF0 or _xF1 or _xF2 or _xF3 or _xF4 or _xF5 or _xF6 or _xF7 or _xF8 or _xF9 or _xFA or _xFB or _xFC or _xFD or _xFE or _xFF map { it.text }))

// token-char =             %x21 / %x23-27 / %x2A-2B / %x2D-2E / %x30-39 / %x41-5A / %x5E-7E
internal val tokenChar = _x21 or _x23 or _x24 or _x25 or _x26 or _x27 or _x2A or _x2B or _x2D or _x2E or _x30 or _x31 or _x32 or _x33 or _x34 or _x35 or _x36 or _x37 or _x38 or _x39 or _x41 or _x42 or _x43 or _x44 or _x45 or _x46 or _x47 or _x48 or _x49 or _x4A or _x4B or _x4C or _x4D or _x4E or _x4F or _x50 or _x51 or _x52 or _x53 or _x54 or _x55 or _x56 or _x57 or _x58 or _x59 or _x5A or _x5E or _x5F or _x60 or _x61 or _x62 or _x63 or _x64 or _x65 or _x66 or _x67 or _x68 or _x69 or _x6A or _x6B or _x6C or _x6D or _x6E or _x6F or _x70 or _x71 or _x72 or _x73 or _x74 or _x75 or _x76 or _x77 or _x78 or _x79 or _x7A or _x7B or _x7C or _x7D or _x7E map { it.text }

// token =              1*(token-char)
internal val token = oneOrMore(tokenChar) map { it.text }

// extn-addr =          non-ws-string
internal val extnAddr = nonWsString

// byte-string =        1*(%x01-09/%x0B-0C/%x0E-FF)
//                      ; any byte e_xcept NUL, CR, or LF
internal val byteString: Parser<String> = oneOrMore(_x01 or _x02 or _x03 or _x04 or _x05 or _x06 or _x07 or _x08 or _x09 or _x0B or _x0C or _x0E or _x0F or _x10 or _x11 or _x12 or _x13 or _x14 or _x15 or _x16 or _x17 or _x18 or _x19 or _x1A or _x1B or _x1C or _x1D or _x1E or _x1F or _x20 or _x21 or _x22 or _x23 or _x24 or _x25 or _x26 or _x27 or _x28 or _x29 or _x2A or _x2B or _x2C or _x2D or _x2E or _x2F or _x30 or _x31 or _x32 or _x33 or _x34 or _x35 or _x36 or _x37 or _x38 or _x39 or _x3A or _x3B or _x3C or _x3D or _x3E or _x3F or _x40 or _x41 or _x42 or _x43 or _x44 or _x45 or _x46 or _x47 or _x48 or _x49 or _x4A or _x4B or _x4C or _x4D or _x4E or _x4F or _x50 or _x51 or _x52 or _x53 or _x54 or _x55 or _x56 or _x57 or _x58 or _x59 or _x5A or _x5B or _x5C or _x5D or _x5E or _x5F or _x60 or _x61 or _x62 or _x63 or _x64 or _x65 or _x66 or _x67 or _x68 or _x69 or _x6A or _x6B or _x6C or _x6D or _x6E or _x6F or _x70 or _x71 or _x72 or _x73 or _x74 or _x75 or _x76 or _x77 or _x78 or _x79 or _x7A or _x7B or _x7C or _x7D or _x7E or _x7F or _x80 or _x81 or _x82 or _x83 or _x84 or _x85 or _x86 or _x87 or _x88 or _x89 or _x8A or _x8B or _x8C or _x8D or _x8E or _x8F or _x90 or _x91 or _x92 or _x93 or _x94 or _x95 or _x96 or _x97 or _x98 or _x99 or _x9A or _x9B or _x9C or _x9D or _x9E or _x9F or _xA0 or _xA1 or _xA2 or _xA3 or _xA4 or _xA5 or _xA6 or _xA7 or _xA8 or _xA9 or _xAA or _xAB or _xAC or _xAD or _xAE or _xAF or _xB0 or _xB1 or _xB2 or _xB3 or _xB4 or _xB5 or _xB6 or _xB7 or _xB8 or _xB9 or _xBA or _xBB or _xBC or _xBD or _xBE or _xBF or _xC0 or _xC1 or _xC2 or _xC3 or _xC4 or _xC5 or _xC6 or _xC7 or _xC8 or _xC9 or _xCA or _xCB or _xCC or _xCD or _xCE or _xCF or _xD0 or _xD1 or _xD2 or _xD3 or _xD4 or _xD5 or _xD6 or _xD7 or _xD8 or _xD9 or _xDA or _xDB or _xDC or _xDD or _xDE or _xDF or _xE0 or _xE1 or _xE2 or _xE3 or _xE4 or _xE5 or _xE6 or _xE7 or _xE8 or _xE9 or _xEA or _xEB or _xEC or _xED or _xEE or _xEF or _xF0 or _xF1 or _xF2 or _xF3 or _xF4 or _xF5 or _xF6 or _xF7 or _xF8 or _xF9 or _xFA or _xFB or _xFC or _xFD or _xFE or _xFF) map { chars -> chars.textOfTokens }

// text =               byte-string
//                      ; default is to interpret this as UTF8 text.
//                      ; ISO 8859-1 requires "a=charset:ISO-8859-1"
//                      ; session-level attribute to be used
internal val text = byteString

internal val Tuple2<String, String>.text2 get() = t1 + t2
internal val Tuple3<String, String, String>.text3 get() = t1 + t2 + t3
internal val Tuple4<String, String, String, String>.text4 get() = t1 + t2 + t3 + t4
internal val Tuple5<String, String, String, String, String>.text5 get() = t1 + t2 + t3 + t4 + t5
internal val Tuple6<String, String, String, String, String, String>.text6 get() = t1 + t2 + t3 + t4 + t5 + t6
internal val Tuple7<String, String, String, String, String, String, String>.text7 get() = t1 + t2 + t3 + t4 + t5 + t6 + t7
internal val List<String>.text get() = joinToString(separator = "")
internal val List<Tuple2<String, String>>.text2 get() = joinToString(separator = "") { it.text2 }
internal val List<Tuple3<String, String, String>>.text3 get() = joinToString(separator = "") { it.text3 }
internal val List<TokenMatch>.textOfTokens get() = joinToString(separator = "") { it.text }
internal infix fun Int.timesAsText(parser: Parser<String>): Parser<String> = this times parser map { it.text }
internal infix fun IntRange.timesAsText(parser: Parser<String>): Parser<String> = this times parser map { it.text }
internal fun zeroOrMoreAsText(parser: Parser<String>): Parser<String> = zeroOrMore(parser) map { it.text }
internal fun oneOrMoreAsText(parser: Parser<String>): Parser<String> = oneOrMore(parser) map { it.text }
