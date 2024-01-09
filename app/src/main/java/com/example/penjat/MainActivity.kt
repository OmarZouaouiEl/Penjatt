package com.example.penjat

import LaunchScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.penjat.ui.theme.PenjatTheme
// MainActivity.kt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PenjatTheme {
                // Aquí se crea el navController y se define la navegación
                val navController = rememberNavController()

                NavHost(navController, startDestination = Routes.SplashScreen.route) {
                    composable(Routes.SplashScreen.route) {
                        SplashScreen(navController)
                    }
                    composable(Routes.MenuScreen.route) {
                        MenuScreen(navController)
                    }
                    composable(Routes.GameScreen.route) { backStackEntry ->
                        val difficulty =
                            backStackEntry.arguments?.getString("difficulty")?.let {
                                Difficulty.valueOf(it)
                            } ?: Difficulty.EASY
                        GameScreen(navController, difficulty)
                    }
                    composable(Routes.ResultScreen.route) { backStackEntry ->
                        val isVictory =
                            backStackEntry.arguments?.getBoolean("isVictory") ?: false
                        ResultScreen(isVictory, navController)
                    }
                }
            }
        }
    }
}
