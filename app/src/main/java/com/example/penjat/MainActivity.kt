import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.penjat.GameScreen
import com.example.penjat.MenuScreen
import com.example.penjat.ResultScreen
import com.example.penjat.ui.theme.PenjatTheme
import com.example.penjat.Routes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PenjatTheme {
                // Aquí se crea el navController y se define la navegación
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

    enum class Difficulty(val maxAttempts: Int) {
        EASY(10),
        MEDIUM(7),
        HARD(5)
    }
}
