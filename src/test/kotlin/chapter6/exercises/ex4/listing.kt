package chapter6.exercises.ex4

// import chapter3.Cons
// import chapter3.Nil
import chapter3.Cons
import chapter3.List
import chapter3.Nil
import chapter6.RNG
import chapter6.rng1
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

// TODO: Enable tests by removing `!` prefix
class Exercise4 : WordSpec({

    // tag::init[]
    fun ints(count: Int, rng: RNG): Pair<List<Int>, RNG> =
        if (count > 0) {
            val (i, rng2) = rng.nextInt()
            val (xi, rng3) = ints(count - 1, rng2)
            Cons(i, xi) to rng3
        } else Nil to rng
    // end::init[]

    "ints" should {
        "generate a list of ints of a specified length" {

            ints(5, rng1) shouldBe (List.of(1, 1, 1, 1, 1) to rng1)
        }
    }
})
