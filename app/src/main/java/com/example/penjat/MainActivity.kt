package com.example.penjat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.penjat.ui.theme.PenjatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PenjatTheme {
                // Here we create the navController and define the navigation
                val navController = rememberNavController()

                NavHost(navController, startDestination = Routes.LaunchScreen.route) {
                    composable(Routes.LaunchScreen.route) {
                        LaunchScreen(navController)
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
                    composable(
                        Routes.ResultScreen.route,
                        arguments = listOf(navArgument("isVictory") { type = NavType.BoolType })
                    ) { backStackEntry ->
                        val isVictory =
                            backStackEntry.arguments?.getBoolean("isVictory") ?: false
                        ResultScreen(isVictory, navController)
                    }
                }
            }
        }
    }

    @Composable
    private fun LaunchScreen(navController: NavHostController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.colgate),
                contentDescription = "logo",
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate(Routes.MenuScreen.route) }) {
                Text("Start Game")
            }
        }
    }

    @Composable
    private fun MenuScreen(navController: NavHostController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Choose Difficulty:")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                navController.navigate(
                    Routes.GameScreen.createRoute(
                        difficulty = Difficulty.EASY
                    )
                )
            }) {
                Text("Easy")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                navController.navigate(
                    Routes.GameScreen.createRoute(
                        difficulty = Difficulty.MEDIUM
                    )
                )
            }) {
                Text("Medium")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                navController.navigate(
                    Routes.GameScreen.createRoute(
                        difficulty = Difficulty.HARD
                    )
                )
            }) {
                Text("Hard")
            }
        }
    }

    @Composable
    private fun ResultScreen(isVictory: Boolean, navController: NavHostController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(if (isVictory) "Congratulations! You Won!" else "Game Over! You Lost!")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate(Routes.MenuScreen.route) }) {
                Text("Back to Menu")
            }
        }
    }
}
