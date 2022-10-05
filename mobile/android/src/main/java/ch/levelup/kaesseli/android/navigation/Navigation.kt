package ch.levelup.kaesseli.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ch.levelup.kaesseli.state.AppState
import ch.levelup.kaesseli.android.screens.*

@Composable
fun Navigation(
    startDestination: String,
    appState: AppState
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(
                appState,
                onNavigateToOverview = { navController.navigate(Screen.OverviewScreen.route) },
                onNavigateToRegister = { navController.navigate(Screen.RegisterScreen.route) },
                onNavigateToBalance = { navController.navigate(Screen.BalanceScreen.route) },
                onNavigateToCodeSubmission = { navController.navigate(Screen.CodeSubmissionScreen.route) },
                onNavigateToRecompositionTest = { navController.navigate(Screen.RecompositionTestScreen.route) }
            ) { navController.navigate(Screen.NetworkTestScreen.route) }
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
                onNavigateToBalance = { navController.navigate(Screen.BalanceScreen.route) },
                appState
            )
        }
        composable(route = Screen.CodeSubmissionScreen.route) {
            CodeSubmissionScreen(
                onNavigateToOverview = { navController.navigate(Screen.OverviewScreen.route) },
                onNavigateToRegister = { navController.navigate(Screen.RegisterScreen.route) }
            )
        }
        composable(route = Screen.RecompositionTestScreen.route){
            RecompositionTestScreen(appState)
        }
        composable(route = Screen.NetworkTestScreen.route){
            NetworkTestScreen()
        }
    }
}
