package com.android.vengateshm.androidpractice.kotlin_apis.extension_functions

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.Month
import java.util.*
import kotlin.math.pow

data class User(val id: Int, val name: String)

class Todo {
    var title: String = ""
    var description: String = ""
    var priority: Int = 0

    operator fun component1() = this.title
    operator fun component2() = this.description
    operator fun component3() = this.priority
}

@RequiresApi(Build.VERSION_CODES.O)
fun main() {
    val (id, name) = User(100, "Vengatesh")
    val user = User(101, "Ashton")
    println(id)
    println(name)
    println(user.component1())
    println(user.component2())

    val todo = Todo().apply {
        title = "Buy Milk and Eggs"
        description = "Go to market and buy"
        priority = 4
    }
    val (title, description, priority) = todo
    println(title)
    println(description)
    println(priority)

    val today = LocalDate.parse("2022-11-20")
    val (year, month, day) = today
    println("Year - $year Month - $month Day - $day")

    "Hello Extension Functions!"()
    1()
    0.0f()
    1_000_000L()
    0.0()
    true()
    null()
    listOf("Apples", "Oranges", "Banana")()

    println(3(2 + 5))

    val num = 597
    println(num[0])
    println(num[1])
    println(num[2])
    println(num[3])

    println(double[12])
    println(double(12))
    println(double { 9 })
    println(toUpper["orange"])
    println(toUpper("apple"))
    println(toUpper { "strawberry" })

    val map = TreeMap<String, String>()
    map["b"] = "bravo"
    map["a"] = "alpha"
    map["d"] = "delta"
    map["e"] = "echo"
    map["c"] = "charlie"

    println(map["d"])
    println(map[3])
    println(map[4])
    println(map[0])
    //println(map[5])

    //val evenNumbers = Int[2,4,6,8,10]
    val evenNumbers = Int(2, 4, 6, 8, 10)
    evenNumbers.forEach(::println)

    val date = LocalDate.parse("2022-03-11")
    println(date in Month.MARCH)
    println(date in 2022)

    println(date in Month.MARCH of 2022)
}

@RequiresApi(Build.VERSION_CODES.O)
private operator fun LocalDate.component3(): Int {
    return this.dayOfMonth
}

@RequiresApi(Build.VERSION_CODES.O)
private operator fun LocalDate.component2(): Int {
    return this.monthValue
}

@RequiresApi(Build.VERSION_CODES.O)
private operator fun LocalDate.component1(): Int {
    return this.year
}

operator fun Any?.invoke() {
    println(this)
}

operator fun Int.invoke(other: Int) = this * other

operator fun Int.get(digit: Int): Int /*{
    val powerOf = 10.0.pow(digit.toDouble())
    val div = this.div(powerOf)
    val rem = div.rem(10.0)
    return rem.toInt()
}*/ = div(10.0.pow(digit.toDouble()))
    .rem(10.0)
    .toInt()

val double: (Int) -> Int = { it * 2 }
val toUpper: (String) -> String = { it.uppercase() }

private operator fun <T, R> ((T) -> R).get(param: T): R {
    return this(param)
}

private operator fun <T, R> ((T) -> R).invoke(paramProvider: () -> T): R {
    return this(paramProvider())
}

private operator fun <K, V> TreeMap<K, V>.get(index: Int): V? {
    return this.values.elementAt(index)
}

private operator fun Int.Companion.get(vararg items: Int): IntArray = intArrayOf(*items)

private operator fun Int.Companion.invoke(vararg items: Int): IntArray = intArrayOf(*items)

@RequiresApi(Build.VERSION_CODES.O)
private operator fun Month.contains(date: LocalDate): Boolean {
    return date.month == this
}

@RequiresApi(Build.VERSION_CODES.O)
private operator fun Int.contains(date: LocalDate): Boolean {
    return date.year == this
}

infix fun Month.of(year: Int): Pair<Month, Int> = this to year

@RequiresApi(Build.VERSION_CODES.O)
private operator fun Pair<Month, Int>.contains(date: LocalDate): Boolean {
    return date in first && date in second
}