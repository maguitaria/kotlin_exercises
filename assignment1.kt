
import kotlin.random.Random

fun main() {
    print("Hello, this is an exercise 2.")

// Exercise 2

    val randomNumber = Random.nextInt(-10, 11)
    println("\nRandom number : $randomNumber")

    when {
        // when condition is similar to switch in C-like languages
        randomNumber < 0 -> println("The number is negative.")
        randomNumber == 0 -> println("The number is zero.")
        randomNumber > 0 -> println("The number is positive.")
    }

}

