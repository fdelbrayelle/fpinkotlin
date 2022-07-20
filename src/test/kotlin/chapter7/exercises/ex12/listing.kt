package chapter7.exercises.ex12

import java.util.concurrent.ExecutorService
import java.util.concurrent.Future

typealias Par<A> = (ExecutorService) -> Future<A>

// tag::init[]
fun <A, B> chooser(pa: Par<A>, choices: (A) -> Par<B>): Par<B> = { es ->
    choices(pa.invoke(es).get()).invoke(es)
}
// end::init[]
