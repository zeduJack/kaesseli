package ch.levelup.kaesseli.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import ch.levelup.kaesseli.state.AppState
import ch.levelup.kaesseli.android.navigation.Navigation
import ch.levelup.kaesseli.android.navigation.ScreenNavigation
import ch.levelup.kaesseli.android.ui.theme.JetpackComposeTestTheme
import org.reduxkotlin.StoreSubscription

class MainActivity : AppCompatActivity() {
    private lateinit var unsubscribe: StoreSubscription

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val state: MutableState<AppState> = mutableStateOf(Store.instance.getState())

        setContent {
            val navController = rememberNavController()
            var startDestination by remember { mutableStateOf(ScreenNavigation.LoginScreen.route) }

            if (false) {
                startDestination = ScreenNavigation.RegisterScreen.route
            }

            JetpackComposeTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation(navController, startDestination, state.value)
                }
            }

            unsubscribe = Store.instance.subscribe {
                if (Store.instance.state.navigation != "" &&
                    Store.instance.state.navigation != navController.currentDestination?.route
                ) {
                    navController.navigate(route = Store.instance.state.navigation)
                }
                state.value = Store.instance.state
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unsubscribe()
    }
}


