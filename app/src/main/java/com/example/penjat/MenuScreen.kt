package com.example.penjat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun MenuScreen(navController: NavController) {
    var selectedDifficulty by remember { mutableStateOf<Difficulty?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "Select Difficulty",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.1.sp
            )
        )

        DifficultyButton(
            difficulty = Difficulty.EASY,
            selectedDifficulty = selectedDifficulty,
            onClick = { selectedDifficulty = Difficulty.EASY }
        )

        DifficultyButton(
            difficulty = Difficulty.MEDIUM,
            selectedDifficulty = selectedDifficulty,
            onClick = { selectedDifficulty = Difficulty.MEDIUM }
        )

        DifficultyButton(
            difficulty = Difficulty.HARD,
            selectedDifficulty = selectedDifficulty,
            onClick = { selectedDifficulty = Difficulty.HARD }
        )

        Button(
            onClick = {
                selectedDifficulty?.let {
                    navigateToGameScreen(navController, it)
                }
            },
            enabled = selectedDifficulty != null
        ) {
            Text(text = "Start Game")
        }
    }
}

@Composable
fun DifficultyButton(difficulty: Difficulty, selectedDifficulty: Difficulty?, onClick: () -> Unit) {
    val isSelected = difficulty == selectedDifficulty

    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
        )
    ) {
        Text(text = difficulty.name, color = if (isSelected) Color.White else Color.Black)
    }
}

fun navigateToGameScreen(navController: NavController, difficulty: Difficulty) {
    val route = Routes.GameScreen.createRoute(difficulty)
    navController.navigate(route)
}