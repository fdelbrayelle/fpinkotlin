package chapter5.exercises.ex1

import chapter3.List
import chapter3.Nil
import chapter3.reverse
import chapter5.Cons
import chapter5.Empty
import chapter5.Stream
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

// TODO: Enable tests by removing `!` prefix
class Exercise1 : WordSpec({
    // tag::init[]
    fun <A> Stream<A>.toList(): List<A> {
        tailrec fun go(xs: Stream<A>, acc: List<A>): List<A> = when (xs) {
            is Empty -> acc
            is Cons -> go(xs.tail(), chapter3.Cons(xs.head(), acc))
        }
        return reverse(go(this, Nil))
    }
    // end::init[]

    "Stream.toList" should {
        "force the stream into an evaluated list" {
            val s = Stream.of(1, 2, 3, 4, 5)
            s.toList() shouldBe List.of(1, 2, 3, 4, 5)
        }
    }
})
