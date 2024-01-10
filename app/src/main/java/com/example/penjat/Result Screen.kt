package com.example.penjat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// ResultScreen.kt

@Composable
fun ResultScreen(isVictory: Boolean, navController: NavController, difficulty: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = if (isVictory) "Victory!" else "Game Over!",
            style = TextStyle.Default.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = if (isVictory) Color.Green else Color.Red
            )
        )

        Button(
            onClick = {
                navController.navigate(Routes.MenuScreen.route)
            }
        ) {
            Text(text = "Return to Menu")
        }
    }
}
