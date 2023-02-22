package ch.levelup.kaesseli.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ch.levelup.kaesseli.ScreenNavigation
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
                onNavigateToRegister = { navController.navigate(ScreenNavigation.RegisterScreen.route) },
                onNavigateToBalance = { navController.navigate(ScreenNavigation.AccountScreeen.route) },
                onNavigateToCodeSubmission = { navController.navigate(ScreenNavigation.CodeSubmissionScreen.route) },
            )
        }
        composable(route = ScreenNavigation.LoginScreen.route) {
            LayoutScreen(appState = appState) {
                LoginScreen(appState)
            }
        }
        composable(route = ScreenNavigation.MemberScreen.route) {
            LayoutScreen(appState = appState) {
                MemberScreen(appState)
            }
        }
        composable(route = ScreenNavigation.StartScreen.route) {
            LayoutScreen(appState = appState) {
                StartScreen(appState = appState)
            }
        }
        composable(route = ScreenNavigation.GroupMembersScreen.route) {
            LayoutScreen(appState = appState) {
                GroupMembersScreen(appState = appState)
            }
        }
        composable(route = ScreenNavigation.AccountScreeen.route) {
            LayoutScreen(appState = appState) {
                AccountScreen(appState)
            }
        }
        composable(route = ScreenNavigation.RegisterScreen.route) {
            RegisterScreen(
                onNavigateToOverview = { navController.navigate(ScreenNavigation.OverviewScreen.route) }
            )
        }
        composable(route = ScreenNavigation.CodeSubmissionScreen.route) {
            CodeSubmissionScreen(
                onNavigateToOverview = { navController.navigate(ScreenNavigation.OverviewScreen.route) },
                onNavigateToRegister = { navController.navigate(ScreenNavigation.RegisterScreen.route) }
            )
        }
        composable(route = ScreenNavigation.PaymentScreen.route) {
            PaymentScreen(appState = appState)
        }
    }
}
