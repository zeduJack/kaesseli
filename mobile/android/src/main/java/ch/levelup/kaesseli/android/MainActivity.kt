package ch.levelup.kaesseli.android

import android.os.Bundle
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import ch.levelup.kaesseli.ScreenNavigation
import ch.levelup.kaesseli.android.navigation.Navigation
import ch.levelup.kaesseli.android.ui.theme.JetpackComposeTestTheme
import ch.levelup.kaesseli.navigation.NavigationActions
import ch.levelup.kaesseli.state.AppState
import ch.levelup.kaesseli.state.Store
import org.reduxkotlin.StoreSubscription

class MainActivity : AppCompatActivity() {
    private lateinit var unsubscribe: StoreSubscription
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val state: MutableState<AppState> = mutableStateOf(Store.instance.getState())

        setContent {
            val navController = rememberNavController()

            HandleBack(navController)

            var startDestination by remember { mutableStateOf(ScreenNavigation.LoginScreen.route) }

            startDestination = setStartDestination(startDestination)

            SetTheme(navController, startDestination, state)

            unsubscribe = Store.instance.subscribe {
                handleNavigation(navController)
                state.value = Store.instance.state
            }
        }
    }

    private fun handleNavigation(navController: NavHostController) {
        if (currentRouteNotEqualDestionationRoute(navController)) {
            if (navigateBack()) {
                navController.navigate(
                    route = Store.instance.state.navigation.route,
                    navOptions { popUpTo(navController.backQueue[navController.backQueue.size - 3].destination.id) })
            } else {
                navController.navigate(route = Store.instance.state.navigation.route)
            }
        }
    }

    @Composable
    private fun setStartDestination(currentStartDestination: String): String {
        var startDestination = currentStartDestination
        if (false) {
            startDestination = ScreenNavigation.RegisterScreen.route
        }
        return startDestination
    }

    @Composable
    private fun SetTheme(
        navController: NavHostController,
        startDestination: String,
        state: MutableState<AppState>
    ) {
        JetpackComposeTestTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                Navigation(navController, startDestination, state.value)
            }
        }
    }

    @Composable
    private fun HandleBack(navController: NavHostController) {
        BackHandler(enabled = true) {
            if (navController.backQueue.size > 2) {
                val route =
                    navController.backQueue[navController.backQueue.size - 2].destination.route
                        ?: ""
                Store.instance.dispatch(
                    NavigationActions.SetNavigation(
                        ch.levelup.kaesseli.navigation.Navigation(
                            route = route,
                            true
                        )
                    )
                )
            } else if (navController.backQueue.size == 2) {
                finish()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unsubscribe()
    }
}

fun currentRouteNotEqualDestionationRoute(navController: NavHostController): Boolean {
    return Store.instance.state.navigation.route != "" &&
            Store.instance.state.navigation.route != navController.currentDestination?.route
}

fun navigateBack(): Boolean {
    return Store.instance.state.navigation.navigateBack
}