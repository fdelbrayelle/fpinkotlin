package chapter7.exercises.ex10

// import chapter7.sec3.Pars
import chapter7.solutions.ex10.map
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future

typealias Par<A> = (ExecutorService) -> Future<A>

fun <A> choiceN(n: Par<Int>, choices: List<Par<A>>): Par<A> =
    { es: ExecutorService ->
        choices.get(n(es).get()).invoke(es)
    }

fun <A> choice(cond: Par<Boolean>, t: Par<A>, f: Par<A>): Par<A> =
    { es: ExecutorService ->
        choiceN(map(cond) { if (it) 1 else 0 }, listOf(t, f))(es)
    }
