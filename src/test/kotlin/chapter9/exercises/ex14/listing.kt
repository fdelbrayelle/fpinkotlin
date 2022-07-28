package chapter9.exercises.ex14

import arrow.core.Option
import arrow.core.lastOrNone
import chapter9.solutions.final.Location

// tag::init[]
data class ParseError(
    val stack: List<Pair<Location, String>> = emptyList()
) {

    fun push(loc: Location, msg: String): ParseError =
        this.copy(stack = listOf(loc to msg) + stack)

    fun label(s: String): ParseError =
        ParseError(latestLoc().map { it to s }.toList())

    fun latest(): Option<Pair<Location, String>> =
        stack.lastOrNone()

    fun latestLoc(): Option<Location> =
        latest().map { it.first }
}
// end::init[]
