package chapter5.exercises.ex9

import chapter3.List
import chapter5.Stream
import chapter5.Stream.Companion.cons
import chapter5.solutions.ex13.take
import chapter5.toList
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

// TODO: Enable tests by removing `!` prefix
class Exercise9 : WordSpec({

    // tag::init[]
    fun from(n: Int): Stream<Int> =
        cons({ n }, { from(n + 1) })
    // end::init[]

    "from" should {
        "return a Stream of ever incrementing numbers" {
            from(5).take(5).toList() shouldBe
                List.of(5, 6, 7, 8, 9)
        }
    }
})
