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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.setValue


// MenuScreen.kt

@Composable
fun MenuScreen(navController: NavController) {
    var selectedDifficulty by remember { mutableStateOf("easy") }
    var expanded:Boolean

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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = "Difficulty: ${selectedDifficulty}",
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
                    DropdownMenuItem(text = { Text(text = "easy") }, onClick = {
                        expanded = false
                        selectedDifficulty = "Easy"
                    })

                    DropdownMenuItem(text = { Text(text = "medium") }, onClick = {
                        expanded = false
                        selectedDifficulty = "Medium"
                    })

                    DropdownMenuItem(text = { Text(text = "hard") }, onClick = {
                        expanded = false
                        selectedDifficulty= "Hard"
                    })

                }
            }
        }

        // Botón para iniciar el juego con la dificultad seleccionada
        Button(
            onClick = {
                navController.navigate(Routes.gameScreen(selectedDifficulty))
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



