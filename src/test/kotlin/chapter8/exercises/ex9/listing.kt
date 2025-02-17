package chapter8.exercises.ex9

import chapter8.RNG

typealias TestCases = Int

sealed class Result {
    abstract fun isFalsified(): Boolean
}

object Passed : Result() {
    override fun isFalsified(): Boolean = false
}

typealias SuccessCount = Int
typealias FailedCase = String

data class Falsified(
    val failure: FailedCase,
    val successes: SuccessCount
) : Result() {
    override fun isFalsified(): Boolean = true
}

// tag::init[]
data class Prop(val run: (TestCases, RNG) -> Result) {
    fun and(p: Prop): Prop = Prop { n, rng ->
        when (val prop = run(n, rng)) {
            is Passed -> p.run(n, rng)
            is Falsified -> prop
        }
    }

    fun or(p: Prop): Prop = Prop { n, rng ->
        when (val prop = run(n, rng)) {
            is Falsified -> p.tag(prop.failure).run(n, rng)
            is Passed -> prop
        }
    }

    private fun tag(msg: String) = Prop { n, rng ->
        when (val prop = run(n, rng)) {
            is Falsified -> Falsified(
                "$msg : ${prop.failure}",
                prop.successes
            )
            is Passed -> prop
        }
    }
}
// end::init[]
