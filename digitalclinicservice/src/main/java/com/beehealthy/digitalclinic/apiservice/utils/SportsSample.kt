package com.beehealthy.digitalclinic.apiservice.utils

import android.util.Log
import io.reactivex.rxjava3.kotlin.toObservable

// !!! ALERT - Not related to this project.!!!
// Kotlin experimental class to understand more on the built in features
// to implement best practices
class SportsSample {
    val TAG = "Venkat"

    enum class Sport { HIKE, RUN, TOURING_BICYCLE, E_TOURING_BICYCLE }
    data class Summary(val sport: Sport, val distance: Int)

    init {
        // Write kotlin code to print the top sport by distance excluding eBikes.
        val sportStats = listOf(
            Summary(Sport.HIKE, 92),
            Summary(Sport.RUN, 77),
            Summary(Sport.TOURING_BICYCLE, 322),
            Summary(Sport.E_TOURING_BICYCLE, 656)

        )

        val result = sportStats
            .filter { it.sport != Sport.E_TOURING_BICYCLE }
            .sortedWith(compareBy { it.distance }).last()
        println("filter via List operators, sort: $result")

        sportStats.toObservable()
            .filter { it.sport != Sport.E_TOURING_BICYCLE }
            .sorted(compareBy { it.distance }).lastElement()
            .subscribe({ println("onNext: $it") }, {}, { print("action:onComplete") })

//        sampleFunction()

    }

    private fun sampleFunction() {
        val name = "Venkat"
        println("venkat: " + name.length)
        println("venkat:" + name[2])

        val s = "Today is a sunny day."
        Log.d(TAG, s)
        Log.d(TAG, "Old " + "bear")
        Log.d(TAG, "The string has " + s.length + " characters")

        val fullName: String? // nullable
        fullName = null
        if (fullName != null) {
            Log.d(TAG, fullName)
        }

        // double bang operator to throw null pointer exception
//        fullName!!.let { Log.d(TAG, fullName) }

        val a = '2'.digitToInt()
        val b = '3'.digitToInt()
        val sum = a + b
        Log.d(TAG, "$sum")

        val number: Int = 127_09_54

        val byt = number.toByte()
        val str = number.toString()
        val dou = number.toDouble()
        val lng = number.toLong()

        Log.d(TAG, "$byt, $str, $dou, $lng")

        Log.d(TAG, "I like kotlin")
        println("I like kotlin")

//        var (aa, bb) = (10, 20)

        callVariousFunctions()

        // calling when statement sample
        whenCases("Hello")
        whenCases(1)
        whenCases(0L)
//        whenCases(SportsSample())
        whenCases("hello")

        // calling when expression sample
        println(whenAssign("Hello"))
        println(whenAssign(3.4))
        println(whenAssign(1))
//        println(whenAssign(SportsSample()))

        val cakes = listOf("carrot", "cheese", "chocolate")

        for (cake in cakes) {                               // 1
            println("Yummy, it's a $cake cake!")
        }


        val red = Color.RED
        println(red)                                      // 4
        println(red.containsRed())                        // 5
        println(Color.BLUE.containsRed())                 // 6
        println(Color.YELLOW.containsRed())


        //  object expression
        rentPrice(10, 2, 1)

        BigBen.getBongs(12)

        // All examples create a function object that performs upper-casing.
// So it's a function from String to String

        fun loweCase(text: String) : String{
            return text.lowercase()
        }
        // Lambda Functions
        val lowerCase1 = {text: String -> text.lowercase()}
        val lowerCase2: (String) -> String = {it.lowercase()}

        val upperCase1: (String) -> String = { str: String -> str.uppercase() } // 1
        val upperCase2: (String) -> String = { str -> str.uppercase() }         // 2
        val upperCase3 = { str: String -> str.uppercase() }                     // 3
// val upperCase4 = { str -> str.uppercase() }                          // 4
        val upperCase5: (String) -> String = { it.uppercase() }                 // 5
        val upperCase6: (String) -> String = String::uppercase                  // 6

        println(lowerCase1("hello"))

        println(upperCase1("hello"))
        println(upperCase2("hello"))
        println(upperCase3("hello"))
        println(upperCase5("hello"))
        println(upperCase6("hello"))

        // Higher order functions: Passing function as parameter
        val mulResult = calculate(4, 5) {a, b -> a * b}
        val sumResult = calculate(4, 5, ::sum)
        Log.d(TAG, "sumResult $sumResult, mulResult $mulResult")

        // returning functions
        val funcCall = operation1()
        Log.d(TAG, funcCall(2).toString())
    }

    fun calculate(a: Int, b: Int, operation: (Int, Int) -> Int): Int {  // 1
        return operation(a, b)                                          // 2
    }
    fun sum(a: Int, b: Int) = a + b

    fun operation1(): (Int) -> Int {                                     // 1
        return ::square
    }
    fun square(x: Int) = x * x

    fun <T> T?.nullSafeToString() = this?.toString() ?: "NULL"

    fun rentPrice(standardDays: Int, festivityDays: Int, specialDays: Int): Unit {  //1

        val dayRates = object {                                                     //2
            var standard: Int = 30 * standardDays
            var festivity: Int = 50 * festivityDays
            var special: Int = 100 * specialDays
        }

        val total = dayRates.standard + dayRates.festivity + dayRates.special       //3

        print("Total price: $$total")                                               //4

    }

    infix fun String.ontoooo(other: String) = Pair(this, other)
    infix fun Int.timesss(str: String) = str.repeat(this)

    operator fun String.get(range: IntRange) = substring(range)  // 3
    operator fun Int.times(str: String) = str.repeat(this)
    fun printAlllll(vararg messages: String) {                            // 1
        for (m in messages) println(m)
    }

    fun log(vararg entries: String) {
        printAlllll(*entries)                                             // 5
    }

    fun callVariousFunctions() {
        val myPair = "McLaren" ontoooo "Lucas"
        println(myPair)
        println(2 timesss "Bye ")
        val str = "Always forgive your enemies; nothing annoys them so much."
        println(str[0..14])
        println(2 * "Bye ")

        //Vararg sample
        printAllWithPrefix(
            "Hello", "Hallo", "Salut", "Hola", "你好",
            prefix = "Greeting: "                                          // 4
        )
    }

    fun printAllWithPrefix(vararg messages: String, prefix: String) {  // 3
        for (m in messages) println(prefix + m)
    }

    private fun findMax(list: List<Summary?>): Summary? {
        return list
            .filter { it?.sport != Sport.E_TOURING_BICYCLE }
            .sortedWith(compareBy { it?.distance }).last()
    }

    private fun findMax(list: List<Int>): Int? {
        var max = Int.MIN_VALUE
        for (i in list) {
            max = max.coerceAtLeast(i)
        }
        return max
    }

    fun nullSafetyTest() {

//        Null Safety ? symbol
        var neverNull: String = "This can't be null"            // 1
        var nullable: String? = "You can keep a null here"      // 3
        nullable = null                                         // 4
        var inferredNonNull = "The compiler assumes non-null"   // 5
    }

    fun whenCases(obj: Any) {
        when (obj) {                                     // 1
            1 -> println("One")                          // 2
            "Hello" -> println("Greeting")               // 3
            is Long -> println("Long")                   // 4
            !is String -> println("Not a string")        // 5
            else -> println("Unknown")                   // 6
        }
    }

    fun whenAssign(obj: Any): Any {
        val result = when (obj) {                   // 1
            1 -> "one"                              // 2
            "Hello" -> 1                            // 3
            is Long -> false                        // 4
            else -> 42                              // 5
        }
        return result
    }

    enum class Color(val rgb: Int) {                      // 1
        RED(0xFF0000),                                    // 2
        GREEN(0x00FF00),
        BLUE(0x0000FF),
        YELLOW(0xFFFF00);

        fun containsRed() = (this.rgb and 0xFF0000 != 0)  // 3
    }

}


// Example of Generic class, GENERICS

class MutableStack<E>(vararg items: E) {

    private val elements = items.toMutableList()
    fun push(element: E) = elements.add(element)
    fun pop(): E = elements.removeAt(elements.size - 1)
    fun peek(): E = elements.last()
    fun isEmpty() = elements.isEmpty()
    fun size() = elements.size

    override fun toString() = "MutableStack(${elements.joinToString()}})"

    // Generic functions

    init {
        // Generics functions
        val elements = mutableStackOf(3, 4, 5, 6)
    }

    fun <T> mutableStackOf(vararg items: T) = MutableStack(*items)

}

class BigBen {                                  //1
    companion object Bonger {                   //2
        fun getBongs(nTimes: Int) {             //3
            for (i in 1 .. nTimes) {
                print("BONG ")
            }
        }
    }
}

class collectionsListSample(){

    // A list is an ordered collection of items. In Kotlin,
    // lists can be either mutable (MutableList) or read-only (List).
    val systemUsers: MutableList<Int> = mutableListOf(1, 2, 3)
    val sudoers: List<Int> = systemUsers // List is read only

    fun addSystemUser(newUser: Int){
        systemUsers.add(newUser)
    }

    fun getSystemSudoers() : List<Int>{
        return sudoers
    }
}

class collectionSetSample(){

    // A set is an unordered collection that does not support duplicates.
    val openIssues: MutableList<String> = mutableListOf("Issue1", "Issue2", "Issue3")

    fun addIssue(issueText: String) : Boolean{
        return openIssues.add(issueText)
    }

    fun getLogDetails(isAdded: Boolean) : String{
        return if (isAdded) "added" else "Duplicate"
    }
}

class collectionMapSample(){
//    A map is a collection of key/value pairs, where each key is unique
//    and is used to retrieve the corresponding value


}