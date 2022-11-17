package ch.levelup.kaesseli.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ch.levelup.kaesseli.state.AppState
import ch.levelup.kaesseli.android.screens.*

@Composable
fun Navigation(
    navController: NavHostController,
    startDestination: String,
    appState: AppState
) {

    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = ScreenNavigation.MainScreen.route) {
            MainScreen(
                appState,
                onNavigateToOverview = { navController.navigate(ScreenNavigation.OverviewScreen.route) },
                onNavigateToRegister = { navController.navigate(ScreenNavigation.RegisterScreen.route) },
                onNavigateToBalance = { navController.navigate(ScreenNavigation.BalanceScreen.route) },
                onNavigateToCodeSubmission = { navController.navigate(ScreenNavigation.CodeSubmissionScreen.route) },
                onNavigateToRecompositionTest = { navController.navigate(ScreenNavigation.RecompositionTestScreen.route) }
            ) { navController.navigate(ScreenNavigation.NetworkTestScreen.route) }
        }
        composable(route = ScreenNavigation.LoginScreen.route) {
            LayoutScreen(appState = appState) {
                LoginScreen()
            }
        }
        composable(route = ScreenNavigation.UserGroupOverviewScreen.route) {
            LayoutScreen(appState = appState) {
                UserGroupOverviewScreen(appState = appState)
            }
        }
        composable(route = ScreenNavigation.GroupMembersScreen.route) {
            LayoutScreen(appState = appState) {
                GroupMembersScreen(appState = appState)
            }
        }
        composable(route = ScreenNavigation.BalanceScreen.route) {
            BalanceScreen()
        }
        composable(route = ScreenNavigation.RegisterScreen.route) {
            RegisterScreen(
                onNavigateToOverview = { navController.navigate(ScreenNavigation.OverviewScreen.route) }
            )
        }
        composable(route = ScreenNavigation.OverviewScreen.route) {
            OverviewScreen(
                onNavigateToBalance = { navController.navigate(ScreenNavigation.BalanceScreen.route) },
                appState
            )
        }
        composable(route = ScreenNavigation.CodeSubmissionScreen.route) {
            CodeSubmissionScreen(
                onNavigateToOverview = { navController.navigate(ScreenNavigation.OverviewScreen.route) },
                onNavigateToRegister = { navController.navigate(ScreenNavigation.RegisterScreen.route) }
            )
        }
        composable(route = ScreenNavigation.RecompositionTestScreen.route){
            RecompositionTestScreen(appState)
        }
        composable(route = ScreenNavigation.NetworkTestScreen.route){
            NetworkTestScreen()
        }
    }
}
