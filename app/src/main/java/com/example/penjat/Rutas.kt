package com.example.penjat

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType

import androidx.navigation.navArgument

sealed class Routes(val route: String) {
    object LaunchScreen : Routes("launch_screen")
    object MenuScreen : Routes("menu_screen")
    object GameScreen : Routes("game_screen/{difficulty}") {
        fun createRoute(difficulty: Difficulty): String {
            return "game_screen/${difficulty.name}"
        }

        val arguments: List<NamedNavArgument>
            get() = listOf(navArgument("difficulty") { type = NavType.StringType })
    }

    object ResultScreen : Routes("result_screen") {
        fun createRoute(isVictory: Boolean, difficulty: Difficulty): String {
            return "result_screen?isVictory=$isVictory&difficulty=${difficulty.name}"
        }

        val arguments: List<NamedNavArgument>
            get() = listOf(
                navArgument("isVictory") { type = NavType.BoolType },
                navArgument("difficulty") { type = NavType.StringType }
            )
    }
}
