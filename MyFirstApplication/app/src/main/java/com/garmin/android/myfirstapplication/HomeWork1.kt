package com.garmin.android.myfirstapplication

import kotlin.math.sqrt

//Ex1
//A
open class Account(var balance: Double) {
    fun deposit(amount: Double) {
        balance += amount
    }

    open fun withdraw(amount: Double) {
        balance -= amount
    }
}

val account = Account(100.0)

fun test1A() {
    account.deposit(200.00)
    println(account.balance)
    account.withdraw(20.00)
    println(account.balance)
}
//B

class SavingAccount(balance: Double) : Account(balance) {
    override fun withdraw(amount: Double) {
        if (amount < balance) {
            balance -= amount;
        } else {
            println("You don't have enough money to complete this operation")
        }
    }
}

val savingAccount = SavingAccount(150.0)
fun test1B() {
    savingAccount.withdraw(200.0)
    savingAccount.withdraw(30.0)
    println(savingAccount.balance)
}

//Ex2
fun convertAngleToCardinal(angle: Int): String {
    return when (angle) {
        in 0..90 -> "N"
        in 91..180 -> "E"
        in 181..270 -> "S"
        in 271..360 -> "W"
        else -> "That's not a good angle"
    }
}

fun test2() {
    convertAngleToCardinal(20)
    convertAngleToCardinal(97)
    convertAngleToCardinal(192)
    convertAngleToCardinal(281)
    convertAngleToCardinal(-1)
    convertAngleToCardinal(400)
}

//Ex3

fun getNumberOfVoewsl(string: String): Int {
    var counter = 0
    string.forEach { c: Char ->
        when (c) {
            'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' -> ++counter
        }
    }
    return counter
}

fun test3() {
    getNumberOfVoewsl("Aquamarine")
    getNumberOfVoewsl("Aqua testing")
    getNumberOfVoewsl("rhythm")
}

//Ex4
//A
class RectangularShape(var x: Int, var y: Int, var width: Int, var height: Int, var color: Int) {
    fun measure() {}
    fun render() {}
}

fun validateShape(shape: RectangularShape): Boolean {
    return when {
        shape.x < 0 || shape.y < 0 -> {
            println("invalid position");
            false
        }
        shape.width > 1024 || shape.height > 1024 -> {
            println("shape too big")
            false
        }
        shape.color < 0 || shape.color > 0xFFFFFF -> {
            println("invalid color")
            false
        }
        else -> true
    }
}

val shape1 = RectangularShape(-10,0,0,0,0)
val shape2 = RectangularShape(0,0,1330,0,0)
val shape3 = RectangularShape(0,0,0,0,-3)
val shape4 = RectangularShape(30,50,70,90,100)

fun test4() {
    validateShape(shape1)
    validateShape(shape2)
    validateShape(shape3)
    validateShape(shape4)
}

//B

fun initShape(shape: RectangularShape?) {
    shape?.apply {
        x = 10
        y = 10
        width = 100
        height = 200
        color = 0xFF0066
    } ?: throw IllegalArgumentException()
}

//C

fun drawShape(shape: RectangularShape?) {
    shape?.also { validateShape(it) }
        ?.also { it.measure() }
        ?.also { it.render() }
}

//Ex5

val data = listOf(4, 6, 34, 9, 2, 4, 7)

fun Int.isPrimeNumber(): Boolean {
    for (i in 2..this / 2) {
        if (this.rem(i) == 0) {
            return false
        }
    }
    return true
}

fun test5() {
    print(data)
    print(data.reversed())
    print(data.distinct())
    print(data.take(3))
    if (data.filter { it > 0 }.size == data.size) {
        print(data)
    }
    data.map { sqrt(it.toDouble()) }.let(::print)
    data.filter { it.rem(2) == 0 }.let(::print)
    data.filter { it.rem(2) != 0 }.map { data.indexOf(it) }.let(::print)

    data.filter { it.isPrimeNumber() }.let(::print)
    data.last { it.isPrimeNumber() }.let(::print)
}





//Ex6
data class Student(val name: String, val address: String, val grade: Int)
val students = listOf(
    Student("John", "Boston", 6), Student("Jacob", "Baltimore", 2),
    Student("Edward", "New York", 7), Student("William", "Providence", 6),
    Student("Alice", "Philadelphia", 4), Student("Robert", "Boston", 7),
    Student("Richard", "Boston", 10), Student("Steven", "New York", 3)
)

fun test6() {
    print(students)
    students.sortedBy { it.name }.let(::print)
    students.map { it.name }.sorted().let(::print)
    students.sortedBy { it.grade }.sortedBy { it.name }.let(::print)
    students.groupBy { it.address }.let(::print)
}