package com.example.penjat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(navController: NavController, difficulty: Difficulty) {
    var wordToGuess by remember { mutableStateOf("") }
    var guessedWord by remember { mutableStateOf(wordToGuess.replace("[a-zA-Z]".toRegex(), "_")) }
    var remainingAttempts by remember { mutableStateOf(difficulty.maxAttempts) }
    var enteredLetters by remember { mutableStateOf(mutableSetOf<Char>()) }
    var inputText by remember { mutableStateOf(TextFieldValue()) }



    fun generateRandomWord(difficulty: Difficulty): String {
        val words = when (difficulty) {
            Difficulty.EASY -> listOf("apple", "banana", "orange", "grape", "melon")
            Difficulty.MEDIUM -> listOf("elephant", "giraffe", "rhinoceros", "hippopotamus", "zebra")
            Difficulty.HARD -> listOf("exaggerate", "serendipity", "ambiguity", "preposterous", "perpendicular")
        }
        return words.random().uppercase()
    }

    wordToGuess = generateRandomWord(difficulty = difficulty)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "Remaining Attempts: $remainingAttempts",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.1.sp
            )
        )

        Image(
            painter = painterResource(id = R.drawable.colgate),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .padding(16.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "Word: $guessedWord",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.1.sp
            )
        )

        TextField(
            value = inputText,
            onValueChange = {
                inputText = it
            },
            label = { Text("Enter a letter") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            for (letter in 'A'..'Z') {
                LetterButton(
                    letter = letter,
                    onClick = {
                        var Funciona: Boolean =  handleLetterClick(letter, enteredLetters, wordToGuess, guessedWord, remainingAttempts)
                        if (!Funciona){
                           remainingAttempts--
                        }
                    },
                    enabled = !enteredLetters.contains(letter)
                )
            }
        }

        if (remainingAttempts == 0 || guessedWord == wordToGuess) {
            navigateToResultScreen(navController, remainingAttempts, difficulty)
        }
    }
}

@Composable
fun LetterButton(letter: Char, onClick: () -> Unit, enabled: Boolean) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            //.width(40.dp)
            .height(80.dp)
    ) {
        Text(text = letter.toString())
    }
}

//

private fun handleLetterClick(
    letter: Char,
    enteredLetters: MutableSet<Char>,
    wordToGuess: String,
    guessedWord: String,
    remainingAttempts: Int
): Boolean {
    if (letter !in enteredLetters) {
        enteredLetters.add(letter)
       if (letter in enteredLetters)
            updateGuessedWord(letter, wordToGuess, guessedWord)

        return true
    }
    return false
}



private fun updateGuessedWord(letter: Char, wordToGuess: String, guessedWord: String): String {
    val updatedGuessedWord = StringBuilder(guessedWord)
    for (i in wordToGuess.indices) {
        if (wordToGuess[i] == letter) {
            updatedGuessedWord[i] = letter
        }
    }
    return updatedGuessedWord.toString()
}



private fun navigateToResultScreen(navController: NavController, remainingAttempts: Int, difficulty: Difficulty) {
    val route = Routes.ResultScreen.createRoute(isVictory = remainingAttempts > 0, difficulty = difficulty)
    navController.navigate(route)
}

private fun generateRandomWord(difficulty: Difficulty): String {
    val words = when (difficulty) {
        Difficulty.EASY -> listOf("manzana", "Banana", "Naranja", "Grapa", "melon")
        Difficulty.MEDIUM -> listOf("elefante", "girafa", "cablejat", "titani", "zebra")
        Difficulty.HARD -> listOf("exagerar", "contrasenya", "ambiguity", "altavoz", "perpendicular")
    }
    return words.random().uppercase()
}
