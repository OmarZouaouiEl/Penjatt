package com.example.penjat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// GameScreen.kt

@Composable
fun GameScreen(navController: NavController, difficulty: Difficulty) {
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
            style = MaterialTheme.typography.h6
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
            style = MaterialTheme.typography.h4
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
                                            guessedWord.substring(0, i) + letter + guessedWord.substring(i + 1)
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
            ResultButton(
                onClick = {
                    // Manejar clic en el botón de resultado (nuevo juego o volver al menú)
                    navController.navigate(ResultScreen()(isVictory = remainingAttempts > 0, difficulty = difficulty))
                },
                label = if (remainingAttempts > 0) "Continue" else "New Game"
            )
        }
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
