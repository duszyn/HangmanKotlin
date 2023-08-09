import java.lang.Exception
import kotlin.random.Random

//defining needed values for an app
val wordsListMusic = arrayListOf("Piano", "Guitar", "Violin", "Trumpet", "Saxophone", "Drums", "Microphone")
val wordsListSports = listOf("Kickboxing", "Brazilian Jujitsu", "Karate", "Taekwondo", "Football", "Basketball", "Handball", "Volleyball")
var wordsListOverall = wordsListMusic + wordsListSports
var word = ""
val guesses = arrayListOf<Char>()
var mistakes = 0

fun main(args: Array<String>) {
    setupGame()


    val blankWord = word.toCharArray()
    for(i in 0..< word.length) {
        blankWord[i] = '_'
    }
    while(blankWord.contains('_') && mistakes < 6) {
        printGameStatus()

        println("You currently made $mistakes/6 mistakes")

        println(String(blankWord))

        println("Tell me a letter you want to guess")

        var userInput = readLine()
        userInput = userInput?.replace(" ", "") //making sure the user didnt put in a value that contains a lot of blanks
        userInput = userInput?.uppercase() //making sure the user doesn't input a lowercase value
        if(userInput?.isNotBlank() == true && userInput.length < 1)
            userInput = userInput.substring(0,1)

        //making sure the user doesn't guess the same letter twice
        val userGuess: Char = userInput?.getOrNull(0)?:'A'
        if(guesses.contains(userGuess)) {
            println("You have already guessed this letter!")
            continue
        }
        //if user guessed the letter correctly
        if(word.toCharArray().contains(userGuess)) {
            for(i in 0..<word.length) {
                if(word.toCharArray()[i] == userGuess) {
                    blankWord[i] = userGuess
                }
            }
            guesses.add(userGuess)
            if(!blankWord.contains('_')) println("YOU WON, CONGRATULATIONS! THE WORD WAS $word")
        } else {
            mistakes++
            if(mistakes == 6) println("YOU LOST! THE WORD WAS $word")
        }
    }
}

//creating a function for displaying current hangman progress
fun printGameStatus() {
    when(mistakes) {
        0 -> print0Mistakes()
        1 -> print1Mistake()
        2 -> print2Mistakes()
        3 -> print3Mistakes()
        4 -> print4Mistakes()
        5 -> print5Mistakes()
        6 -> print6Mistakes()
    }
}
//setting up the game
fun setupGame() {
    println("What category of a word would you like? 1. Music  2. Sports  3. Music + Sports")
    val userChoice = readLine()?:"3"
    var userChoiceInInt = 3
    try {
        userChoiceInInt = userChoice.toInt()
    } catch (e : Exception){
        println("Input is not an integer, so I chose number 3 for you.")
        e.printStackTrace()
    }
    //choosing random word for a user
    word = when(userChoiceInInt) {
        1 -> wordsListMusic[Random.nextInt(0,wordsListMusic.size-1)]
        2 -> wordsListSports[Random.nextInt(0, wordsListSports.size-1)]
        else -> wordsListOverall[Random.nextInt(0, wordsListOverall.size-1)]
    }.uppercase()
}

//simple pixelarts showing hangman progression
fun print0Mistakes() {
    println("   |------|-")
    println("   |      | ")
    println("   |        ")
    println("   |        ")
    println("   |        ")
    println("   |        ")
    println("  /|\\        ")
    println(" / | \\        ")
}

fun print1Mistake() {
    println("   |------|-")
    println("   |      | ")
    println("   |      O ")
    println("   |        ")
    println("   |        ")
    println("   |        ")
    println("  /|\\        ")
    println(" / | \\        ")
}

fun print2Mistakes() {
    println("   |------|- ")
    println("   |      |  ")
    println("   |      O  ")
    println("   |      |  ")
    println("   |      |  ")
    println("   |         ")
    println("  /|\\       ")
    println(" / | \\      ")
}

fun print3Mistakes() {
    println("   |------|- ")
    println("   |      |  ")
    println("   |      O  ")
    println("   |     /|  ")
    println("   |      |  ")
    println("   |         ")
    println("  /|\\       ")
    println(" / | \\      ")
}

fun print4Mistakes() {
    println("   |------|- ")
    println("   |      |  ")
    println("   |      O  ")
    println("   |     /|\\  ")
    println("   |      |  ")
    println("   |         ")
    println("  /|\\       ")
    println(" / | \\      ")
}

fun print5Mistakes() {
    println("   |------|- ")
    println("   |      |  ")
    println("   |      O  ")
    println("   |     /|\\  ")
    println("   |      |  ")
    println("   |     /   ")
    println("  /|\\       ")
    println(" / | \\      ")
}

fun print6Mistakes() {
    println("   |------|- ")
    println("   |      |  ")
    println("   |      O  ")
    println("   |     /|\\  ")
    println("   |      |  ")
    println("   |     / \\  ")
    println("  /|\\       ")
    println(" / | \\      ")
}