import kotlin.random.Random

fun main() {
    println("Введите кол-во философов: ")
    val n = readln().toInt()

    val forks: MutableList<Boolean> = MutableList(n) {true}
    val thinkers: List<Thinker> = List(n) {Thinker(Status.WAIT)}

    repeat(n) {
            chooseFork(getRandThinker(n,thinkers),forks,thinkers)
    }

    var thinkNum = 0
    var eatNum = 0

    thinkers.forEach {
        if(it.status == Status.THINK)
            thinkNum++
        else if (it.status == Status.EAT)
            eatNum++
    }
    println("Кол-во философов которые едят: $eatNum \nКол-во философов которые думают: $thinkNum")
}

fun getRandThinker(n: Int, thinkers:List<Thinker> ): Int {
    var currThinker: Int
    while (true){
        currThinker = Random.nextInt(0,n)
        if (thinkers[currThinker].status == Status.WAIT)
            break
    }
    return currThinker
}

fun chooseFork(i: Int, forks:MutableList<Boolean>, thinkers:List<Thinker>) {
    val prevFork = if (i == 0) forks.size - 1 else i - 1
    val forkIndices = if (Random.nextBoolean()) listOf(i, prevFork) else listOf(prevFork, i)

    val chosenFork = forkIndices.firstOrNull { forks[it] }
    if (chosenFork != null) {
        forks[chosenFork] = false
        thinkers[i].status = Status.EAT
    } else {
        thinkers[i].status = Status.THINK
    }

//    val choice = Random.nextBoolean()
//    var prevFork = i-1
//    if(i == 0){
//       prevFork = forks.size-1
//    }
//    if (choice) {
//        if (forks[i]) {
//            forks[i] = false
//            thinkers[i].status = Status.EAT
//        } else if (forks[prevFork]) {
//            forks[prevFork] = false
//            thinkers[i].status = Status.EAT
//        } else {
//            thinkers[i].status = Status.THINK
//        }
//    } else {
//        if (forks[prevFork]) {
//            forks[prevFork] = false
//            thinkers[i].status = Status.EAT
//        } else if (forks[i]) {
//            forks[i] = false
//            thinkers[i].status = Status.EAT
//        } else {
//            thinkers[i].status = Status.THINK
//        }
//    }
}