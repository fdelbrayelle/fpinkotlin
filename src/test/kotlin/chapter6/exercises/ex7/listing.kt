package chapter6.exercises.ex7

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import chapter3.foldRight
import chapter6.RNG
import chapter6.Rand
import chapter6.rng1
import chapter6.solutions.ex6.map2
import chapter6.unit
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

// TODO: Enable tests by removing `!` prefix
class Exercise7 : WordSpec({

    // tag::init[]
    fun <A> sequence(fs: List<Rand<A>>): Rand<List<A>> = { rng ->
        when (fs) {
            is Nil -> unit(List.empty<A>())(rng)
            is Cons -> {
                val (a, nrng) = fs.head(rng)
                val (xa, frng) = sequence(fs.tail)(nrng)
                Cons(a, xa) to frng
            }
        }
    }
    // end::init[]

    // tag::init2[]
    fun <A> sequence2(fs: List<Rand<A>>): Rand<List<A>> =
        foldRight(fs, unit(List.empty())) { f, acc ->
            map2(f, acc) { h, t -> Cons(h, t) }
        }
    // end::init2[]

    fun ints2(count: Int, rng: RNG): Pair<List<Int>, RNG> {
        fun go(c: Int): List<Rand<Int>> =
            if (c == 0) Nil
            else Cons({ rng -> 1 to rng }, go(c - 1))
        return sequence2(go(count))(rng)
    }

    "sequence" should {

        "combine the results of many actions using recursion" {

            val combined: Rand<List<Int>> =
                sequence(
                    List.of(
                        unit(1),
                        unit(2),
                        unit(3),
                        unit(4)
                    )
                )

            combined(rng1).first shouldBe
                List.of(1, 2, 3, 4)
        }

        """combine the results of many actions using
            foldRight and map2""" {

            val combined2: Rand<List<Int>> =
                sequence2(
                    List.of(
                        unit(1),
                        unit(2),
                        unit(3),
                        unit(4)
                    )
                )

            combined2(rng1).first shouldBe
                List.of(1, 2, 3, 4)
        }
    }

    "ints" should {
        "generate a list of ints of a specified length" {
            ints2(4, rng1).first shouldBe
                List.of(1, 1, 1, 1)
        }
    }
})
