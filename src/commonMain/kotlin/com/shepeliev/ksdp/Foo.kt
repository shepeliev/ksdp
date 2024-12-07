package com.shepeliev.ksdp

import com.github.h0tk3y.betterParse.combinators.and
import com.github.h0tk3y.betterParse.combinators.map
import com.github.h0tk3y.betterParse.grammar.Grammar
import com.github.h0tk3y.betterParse.grammar.tryParseToEnd
import com.github.h0tk3y.betterParse.lexer.literalToken

object Foo : Grammar<String>() {
    val a by literalToken("a")
    val b by literalToken("b")

    override val rootParser by a map { it.text } orNext (a and b map { it.t1.text + it.t2.text })
}

fun main() {
    println(Foo.tryParseToEnd("a"))
    println(Foo.tryParseToEnd("ab"))
}