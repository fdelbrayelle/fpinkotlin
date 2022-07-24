package chapter8.exercises.ex14

import arrow.core.extensions.list.foldable.exists
import chapter8.Gen
import chapter8.Prop
import chapter8.Prop.Companion.forAll
import chapter8.SGen
import chapter8.sec4_1.run

val smallInt = Gen.choose(-10, 10)

fun List<Int>.prepend(i: Int) = listOf(i) + this

fun maxProp(): Prop = forAll(SGen.listOf(smallInt)) { ns ->
    val nss = ns.sorted()
    nss.isEmpty() or
        (nss.size == 1) or
        nss.zip(nss.prepend(Int.MIN_VALUE))
            .foldRight(true) { p, b ->
                val (pa, pb) = p
                b && (pa >= pb)
            } and
        nss.containsAll(ns) and
        !nss.exists { !ns.contains(it) }
}

fun main() {
    run(maxProp())
}
