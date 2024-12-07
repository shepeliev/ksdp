package com.shepeliev.ksdp

import com.github.h0tk3y.betterParse.combinators.OrCombinator
import com.github.h0tk3y.betterParse.lexer.TokenMatchesSequence
import com.github.h0tk3y.betterParse.parser.*

/** Tries to parse the sequence with the [parsers] until one succeeds. Returns its [Parsed] result in this case.
 * If none succeeds, returns the [AlternativesFailure] with all the [ErrorResult]s. */
public class OrCombinatorWithLookahead<T>(public val parsers: List<Parser<T>>) : Parser<T> {
    private val orCombinator = OrCombinator(parsers)

    override fun tryParse(tokens: TokenMatchesSequence, fromPosition: Int): ParseResult<T> {
        var failures: ArrayList<ErrorResult>? = null
        for (parser in parsers) {
            when (val result = parser.tryParse(tokens, fromPosition)) {
                is Parsed -> {
                    tokens.getNotIgnored(result.nextPosition)?.let {
                        if (failures == null) failures = ArrayList()
                        failures?.add(UnparsedRemainder(it))
                    } ?: return result
                }

                is ErrorResult -> {
                    if (failures == null) failures = ArrayList()
                    failures?.add(result)
                }
            }
        }
        return AlternativesFailure(failures.orEmpty())
    }
}

public infix fun <A> Parser<A>.orNext(other: Parser<A>): Parser<A> {
    val leftParsers = if (this is OrCombinatorWithLookahead) parsers else listOf(this)
    val rightParsers = if (other is OrCombinatorWithLookahead) other.parsers else listOf(other)
    return OrCombinatorWithLookahead(leftParsers + rightParsers)
}

infix operator fun <A> Parser<A>.div(other: Parser<A>): Parser<A> = this orNext other