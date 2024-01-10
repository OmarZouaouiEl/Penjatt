package com.example.penjat

// Routes.kt

sealed class Routes(val route: String) {
    object MainActivity : Routes("pantalla1")
    object LaunchScreen : Routes("pantalla2")
    object MenuScreen : Routes("pantalla3")
    object GameScreen : Routes("pantalla4")
    object ResultScreen : Routes("result_screen/{isVictory}/{difficulty}") {
        fun gameScreen(difficulty: String, isVictory: Boolean) : String {
            return "game_screen/${isVictory}/${difficulty.lowercase()}"
        }
    }
}
