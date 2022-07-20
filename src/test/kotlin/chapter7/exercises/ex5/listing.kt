package chapter7.exercises.ex5

// import chapter7.sec1.splitAt
// import chapter7.solutions.ex3.TimedMap2Future
// import java.util.concurrent.Callable
import chapter7.sec1.splitAt
import chapter7.solutions.ex5.Pars
import chapter7.solutions.ex5.Pars.head
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future

typealias Par<A> = (ExecutorService) -> Future<A>

// tag::init1[]
fun <A> sequence(ps: List<Par<A>>): Par<List<A>> =
    when {
        ps.isEmpty() -> Pars.unit(Pars.Nil)
        ps.size == 1 -> Pars.map(ps.head) { listOf(it) }
        else -> {
            val (l, r) = ps.splitAt(ps.size / 2)
            Pars.map2(Pars.sequence(l), Pars.sequence(r)) { la, lb ->
                la + lb
            }
        }
    }
// end::init1[]
