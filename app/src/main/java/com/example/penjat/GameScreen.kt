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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue

// GameScreen.kt
fun generateRandomWord(difficulty: MainActivity.Difficulty): String {
    // Lógica para generar una palabra aleatoria basada en la dificultad
    // (puedes implementar tu propia lógica aquí)
    val words = when (difficulty) {
        MainActivity.Difficulty.EASY -> listOf("rata", "pata", "moco")
        MainActivity.Difficulty.MEDIUM -> listOf("auricular", "girafa", "mancuerna")
        MainActivity.Difficulty.HARD -> listOf("cableado", "tecleado", "relajadamente")
    }
    return words.random()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(navController: NavController, difficulty: MainActivity.Difficulty) {
    var wordToGuess by remember { mutableStateOf(generateRandomWord(difficulty)) }
    var guessedWord by remember { mutableStateOf("_".repeat(wordToGuess.length)) }
    var remainingAttempts by remember { mutableStateOf(difficulty.maxAttempts) }
    var enteredLetters by remember { mutableStateOf(mutableSetOf<Char>()) }
    var inputText by remember { mutableStateOf(TextFieldValue()) }

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
            painter = painterResource(id = R.drawable.hangman_image),
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

        // TextField para introducir letras
        TextField(
            value = inputText,
            onValueChange = {
                inputText = it
                // Puedes manejar la lógica de entrada aquí
            },
            label = { Text("Enter a letter") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    // Puedes manejar la lógica al presionar "Done" aquí
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        // Botones de letras
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
                        if (!enteredLetters.contains(letter)) {
                            enteredLetters.add(letter)
                            if (!wordToGuess.contains(letter)) {
                                remainingAttempts--
                            } else {
                                // Actualizar guessedWord con letras adivinadas correctamente
                                for (i in wordToGuess.indices) {
                                    if (wordToGuess[i] == letter) {
                                        guessedWord =
                                            guessedWord.substring(
                                                0,
                                                i
                                            ) + letter + guessedWord.substring(i + 1)
                                    }
                                }
                            }
                        }
                    },
                    enabled = !enteredLetters.contains(letter)
                )
            }
        }

        if (remainingAttempts == 0 || guessedWord == wordToGuess) {
            // Mostrar mensaje de victoria o derrota
            val route = Routes.ResultScreen.createRoute(
                isVictory = remainingAttempts > 0,
                difficulty = difficulty
            )
            navController.navigate(route)
        }
    }





    @Composable
    fun LetterButton(letter: Char, onClick: () -> Unit, enabled: Boolean) {
        Button(
            onClick = onClick,
            enabled = enabled,
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
        ) {
            Text(text = letter.toString())
        }
    }

    @Composable
    fun ResultButton(onClick: () -> Unit, label: String) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = label)
        }
    }
}
