package com.example.penjat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(isVictory: Boolean, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = if (isVictory) "Victory!" else "Defeat!",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.1.sp,
                color = if (isVictory) Color.Green else Color.Red
            )
        )

        Button(
            onClick = {
                navigateToMenuScreen(navController)
            }
        ) {
            Text(text = "Back to Menu")
        }
    }
}

private fun navigateToMenuScreen(navController: NavController) {
    val route = Routes.MenuScreen.route
    navController.navigate(route)
}
