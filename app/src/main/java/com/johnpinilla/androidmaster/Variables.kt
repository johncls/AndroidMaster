package com.johnpinilla.androidmaster

import java.time.Month

val age: Int = 30

/**
 * Variables
 */
fun main() {

    add(25, 76)
    var result = getMonth(11)
    if(result == "Noviembre"){
        println("Es tu cumple mi rey")
    }

}
fun  getMonth(month: Int) = when(month) {
        1 -> "Enero"
        2 -> "Febrero"
        3 -> "Marzo"
        4 -> "Abril"
        5 -> "Mayo"
        6 -> "Junio"
        7 -> "Julio"
        8 -> "Agosto"
        9 -> "Septiembre"
        10 -> "Octubre"
        11 -> "Noviembre"
        12 -> "Diciembre"
        else -> "Este Mes no exite"
}

fun add(firsNumber: Int, secondNumber: Int) {
    var sum: Int = (firsNumber + secondNumber);
    if (sum > 10) {
        println(firsNumber + secondNumber)
    } else {
        variableNumericas()
    }
}

fun showName() {
    val name = "John Pinilla";

}

fun variableBooleam() {
    /**
     * Variables booleanas
     */
    val booleanExample: Boolean = true
}

fun varaibleAlfanumerico() {
    /**
     * Variables Alfanumericos
     */
    val charExample1: Char = 'e'
    val charExample2: Char = '2'
    val charExample3: Char = '@'
}

fun variableNumericas() {
    //Variables numericas
    val age2: Int = 29
    val example: Long = 30
    val floatExample: Float = 30.5f
    val doubleExample: Double = 30545.06545646
    println("Sumar:")
    println(age + age2)

    println("Restar:")
    println(age - age2)

    println("Division:")
    println(age / age2)

    println("Multilicar:")
    println(age * age2)

    println("modulo:")
    println(age % age2)
}