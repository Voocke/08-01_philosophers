import kotlin.random.Random

fun main() {
    println("Введите кол-во философов: ")
    var n = readln().toInt()

    val forks: MutableList<Boolean> = MutableList(5) {true}

    val thinkers: List<Thinker> = List(n) {Thinker(Status.WAIT)}

    var startThinker = Random.nextInt(0,n-1)



    for(i in startThinker .. n){
        if (i == n){
            n = startThinker
            startThinker = 0
        }

        val choice = Random.nextBoolean()
        var current = i
        //  есть 1 есть 2 выбрали 1
        //  не есть 1 есть 2 выбрали 1
        //  есть 1 не есть 2 выбрали 1
        //  есть 1 есть 2 не выбрали 1
        //  есть 1 есть 2 выбрали 1
        //  есть 1 есть 2 выбрали 1
        //  есть 1 есть 2 выбрали 1


        if (!(choice && forks[i])){
            current -= 1
            if (!forks[current]){
                current += 1
            }
        }

        if(forks[current]){
            thinkers[i].status = Status.EAT
        }
        else{
            thinkers[i].status = Status.THINK
        }


        val chosenFork = when(choice) {
            true -> {
                forks[i]
            }
            false -> {
                forks[i-1]
            }
        }




        if (choice){
            if (forks[i]) {
                forks[i] = false
                thinkers[i].status = Status.EAT
            }
            else if (forks[i-1]) {
                forks[i-1] = false
                thinkers[i].status = Status.EAT
            }
            else{
                thinkers[i].status = Status.THINK
            }
        }
        else {
            if (forks[i-1]) {
                forks[i-1] = false
                thinkers[i].status = Status.EAT
            }
            else if (forks[i]) {
                forks[i] = false
                thinkers[i].status = Status.EAT
            }
            else{
                thinkers[i].status = Status.THINK
            }
        }

        thinkers[i]
    }

//4-5-6-1-2-3
    //1
    //6
}