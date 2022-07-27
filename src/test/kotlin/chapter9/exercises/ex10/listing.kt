package chapter9.exercises.ex10

import chapter9.exercises.ex5.Parser

interface Parsers {
    // tag::init1[]
    fun <A> furthest(pa: Parser<A>): Parser<A>
    // end::init1[]

    // tag::init2[]
    fun <A> latest(pa: Parser<A>): Parser<A>
    // end::init2[]
}
