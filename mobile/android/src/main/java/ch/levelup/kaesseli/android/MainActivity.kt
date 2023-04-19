package ch.levelup.kaesseli.android

import android.content.Context
import android.content.SharedPreferences
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
import ch.levelup.kaesseli.ScreenNavigation
import ch.levelup.kaesseli.android.navigation.Navigation
import ch.levelup.kaesseli.android.ui.theme.JetpackComposeTestTheme
import ch.levelup.kaesseli.login.LoginActions
import ch.levelup.kaesseli.login.LoginInput
import ch.levelup.kaesseli.navigation.NavigationActions
import ch.levelup.kaesseli.scaffold.ScafffoldActions
import ch.levelup.kaesseli.shared.LogedInUserDto
import ch.levelup.kaesseli.state.AppState
import ch.levelup.kaesseli.state.Store
import ch.levelup.kaesseli.user.UserNetworkThunks
import org.reduxkotlin.StoreSubscription

class MainActivity : AppCompatActivity() {

    val loggedInUserKey = "logged-in-user"

    private lateinit var unsubscribe: StoreSubscription
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val state: MutableState<AppState> = mutableStateOf(Store.instance.getState())

        setContent {
            val navController = rememberNavController()

            HandleBack(navController)

            var startDestination by remember { mutableStateOf(ScreenNavigation.LoginScreen.route) }
            val loggedInUser = getLoggedInUser()
            if (loggedInUser != null && loggedInUser.isNotEmpty()) {
                Store.instance.dispatch(
                    LoginActions.ChangeLogin(
                        LoginInput(
                            username = loggedInUser,
                            true
                        )
                    )
                )
                Store.instance.dispatch(UserNetworkThunks.logInUser())
                startDestination = ScreenNavigation.GroupsScreen.route
            }

            SetTheme(navController, startDestination, state)

            unsubscribe = Store.instance.subscribe {
                if (handleNavigation(navController)) {
                    handleScaffold()
                }
                state.value = Store.instance.state
                saveLoggedInUser(Store.instance.state.logedInUser)
            }
        }
    }

    private fun handleScaffold() {
        Store.instance.dispatch(ScafffoldActions.SetTobBarTitle(Store.instance.state))
    }

    private fun handleNavigation(navController: NavHostController): Boolean {
        var routeChanged = false
        if (currentRouteNotEqualDestionationRoute(navController)) {
            routeChanged = true
            if (navigateBack()) {
                navController.popBackStack()
            } else {
                navController.navigate(route = Store.instance.state.navigation.route)
            }
        }
        return routeChanged
    }

    @Composable
    private fun SetTheme(
        navController: NavHostController,
        startDestination: String,
        state: MutableState<AppState>,
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

    private fun saveLoggedInUser(logedInUser: LogedInUserDto) {
        val sharedPreferences: SharedPreferences = getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(loggedInUserKey, logedInUser.email)
        editor.apply()
    }

    private fun getLoggedInUser(): String? {
        val sharedPreferences: SharedPreferences = getPreferences(Context.MODE_PRIVATE)
        return sharedPreferences.getString(loggedInUserKey, "")
    }
}

fun currentRouteNotEqualDestionationRoute(navController: NavHostController): Boolean {
    return Store.instance.state.navigation.route != "" &&
            Store.instance.state.navigation.route != navController.currentDestination?.route
}

fun navigateBack(): Boolean {
    return Store.instance.state.navigation.navigateBack
}