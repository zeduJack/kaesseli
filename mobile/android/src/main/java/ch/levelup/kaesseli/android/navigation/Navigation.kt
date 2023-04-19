package ch.levelup.kaesseli.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ch.levelup.kaesseli.ScreenNavigation
import ch.levelup.kaesseli.android.screens.*
import ch.levelup.kaesseli.state.AppState

@Composable
fun Navigation(
    navController: NavHostController,
    startDestination: String,
    appState: AppState,
) {

    val floatingActionButtonFunction = remember { mutableStateOf({})}

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
        composable(route = ScreenNavigation.GroupsScreen.route) {
            LayoutScreen(appState = appState) {
                GroupsScreen(appState = appState)
            }
        }
        composable(route = ScreenNavigation.GroupMembersScreen.route) {
            LayoutScreen(appState = appState) {
                GroupMembersScreen(appState = appState)
            }
        }
        composable(route = ScreenNavigation.AccountScreeen.route) {
            LayoutScreen(appState = appState, onFloatingActionButtonClick = {floatingActionButtonFunction.value()}) {
                AccountScreen(appState, floatingActionButtonFunction)
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
        composable(route = ScreenNavigation.CreditScreen.route) {
            LayoutScreen(appState = appState) {
                CreditScreen(appState = appState)
            }
        }
        composable(route = ScreenNavigation.DebitScreen.route) {
            LayoutScreen(appState = appState) {
                DebitScreen(appState = appState)
            }
        }
        composable(route = ScreenNavigation.ProfileScreen.route) {
            LayoutScreen(appState = appState) {
                ProfileScreen()
            }
        }
    }
}
