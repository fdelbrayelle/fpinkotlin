package chapter3.exercises.ex15

import chapter3.Cons
import chapter3.List
import chapter3.List.Companion.of
import chapter3.foldRight
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

// tag::init[]
fun increment(xs: List<Int>): List<Int> =
    // Easiest way: xs.map { it + 1 }
    foldRight(xs, List.empty(), { i: Int, ls -> Cons(i + 1, ls) })
// end::init[]

// TODO: Enable tests by removing `!` prefix
class Exercise15 : WordSpec({
    "list increment" should {
        "add 1 to every element" {
            increment(of(1, 2, 3, 4, 5)) shouldBe
                of(2, 3, 4, 5, 6)
        }
    }
})
