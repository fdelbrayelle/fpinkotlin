package chapter7.exercises.ex6

import chapter7.solutions.ex6.Pars
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future

typealias Par<A> = (ExecutorService) -> Future<A>

//tag::init[]
fun <A> parFilter(
    sa: List<A>,
    f: (A) -> Boolean
): Par<List<A>> {
    val pars: List<Par<A>> = sa.map { Pars.lazyUnit { it } }
    return Pars.map(Pars.sequence(pars)) { la: List<A> ->
        la.flatMap { a ->
            if (f(a)) listOf(a) else emptyList()
        }
    }
}
//end::init[]
