package com.example.penjat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

// MenuScreen.kt

@Composable
fun MenuScreen(navController: NavController) {
    var selectedDifficulty by remember { mutableStateOf(Difficulty.EASY) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // ... (Otros botones existentes)

            // Dropdown para seleccionar la dificultad
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = "Difficulty: ${selectedDifficulty.name}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {

                        }
                        .padding(8.dp)
                )
                DropdownMenu(
                    expanded = false,
                    onDismissRequest = { /* No hacemos nada al cerrar */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    DropdownMenuItem(onClick = {
                        selectedDifficulty = Difficulty.EASY
                    }) {
                        Text(text = "Easy")
                    }
                    DropdownMenuItem(onClick = {
                        selectedDifficulty = Difficulty.MEDIUM
                    }) {
                        Text(text = "Medium")
                    }
                    DropdownMenuItem(onClick = {
                        selectedDifficulty = Difficulty.HARD
                    }) {
                        Text(text = "Hard")
                    }
                }
            }
        }

        // Botón para iniciar el juego con la dificultad seleccionada
        Button(
            onClick = {
                navController.navigate(Routes.GameScreen.route(selectedDifficulty.name))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Icon(imageVector = Icons.Outlined.PlayArrow, contentDescription = null)
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "Start New Game")
        }
    }
}



