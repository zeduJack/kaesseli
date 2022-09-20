package ch.levelup.kaesseli.android.navigation

import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ch.levelup.kaesseli.android.screens.BalanceScreen
import ch.levelup.kaesseli.android.screens.CodeSubmissionScreen
import ch.levelup.kaesseli.android.screens.MainScreen
import ch.levelup.kaesseli.android.screens.OverviewScreen
import ch.levelup.kaesseli.android.screens.RegisterScreen

@Composable
fun Navigation(startDestination: String) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(
                onNavigateToOverview = { navController.navigate(Screen.OverviewScreen.route) },
                onNavigateToRegister = { navController.navigate(Screen.RegisterScreen.route) },
                onNavigateToBalance = { navController.navigate(Screen.BalanceScreen.route) },
                onNavigateToCodeSubmission = { navController.navigate(Screen.CodeSubmissionScreen.route) }
            )
        }
        composable(route = Screen.BalanceScreen.route) {
            BalanceScreen()
        }
        composable(route = Screen.RegisterScreen.route) {
            RegisterScreen(
                onNavigateToOverview = { navController.navigate(Screen.OverviewScreen.route) }
            )
        }
        composable(route = Screen.OverviewScreen.route) {
            OverviewScreen(
                onNavigateToBalance = { navController.navigate(Screen.BalanceScreen.route) }
            )
        }
        composable(route = Screen.CodeSubmissionScreen.route) {
            CodeSubmissionScreen(
                onNavigateToOverview = { navController.navigate(Screen.OverviewScreen.route) },
                onNavigateToRegister = { navController.navigate(Screen.RegisterScreen.route) }
            )
        }
    }
}
