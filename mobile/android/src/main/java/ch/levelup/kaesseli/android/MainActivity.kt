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
import ch.levelup.kaesseli.shared.UserTestDto
import ch.levelup.kaesseli.state.AppState
import ch.levelup.kaesseli.state.Store
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.reduxkotlin.StoreSubscription

class MainActivity : AppCompatActivity() {
    private lateinit var unsubscribe: StoreSubscription
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val state: MutableState<AppState> = mutableStateOf(Store.instance.getState())

        setContent {
            val navController = rememberNavController()

            BackHandler(enabled = true){
                // execute your custome logic here
                println("blaaaaa")

                if(navController.backQueue.size > 2){
                    val route = navController.backQueue[navController.backQueue.size -2].destination.route ?: ""
                    Store.instance.dispatch(NavigationActions.SetNavigation(ch.levelup.kaesseli.navigation.Navigation(route = route, true)))
                } else if(navController.backQueue.size == 2){
                    finish()
                }
            }

            UserTestDto("")
/*
            navController.setLifecycleOwner(this)
            navController.enableOnBackPressed(true)
            val callback = BackCallback(true)
            val dispatcher = OnBackPressedDispatcher()
            dispatcher.addCallback(this,  callback)
            navController.setOnBackPressedDispatcher(dispatcher)

 */


            /*
            navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, bundle: Bundle? ->
                if(Store.instance.state.navigation != nc.currentDestination?.route){
                    val route = nc.currentDestination?.toString() ?: ""
                    Store.instance.dispatch(NavigationActions.SetNavigation(route))
                }
            }

             */


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


                if (currentRouteNotEqualDestionationRoute(navController)) {
                    println("xxxxxxxx: " + navController.backQueue.size)
                    if(navigateBack()){
                        println("xxxxxxxx: " + navController.backQueue.size)
                        navController.navigate(route = Store.instance.state.navigation.route, navOptions { popUpTo(navController.backQueue[navController.backQueue.size -3].destination.id) })
                    } else {
                        println("xxxxxxxx: " + navController.backQueue.size)
                        navController.navigate(route = Store.instance.state.navigation.route)
                    }



                }
                if(navigateBack()){
                    /*
                    navController.backQueue.removeLast()
                    navController.backQueue.removeLast()

                    val route = navController.backQueue.last().destination.route ?: ""
                    Store.instance.dispatch(NavigationActions.SetNavigation(ch.levelup.kaesseli.navigation.Navigation(route = route, false)))

                     */
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

fun currentRouteNotEqualDestionationRoute(navController: NavHostController): Boolean {
    return Store.instance.state.navigation.route != "" &&
            Store.instance.state.navigation.route != navController.currentDestination?.route
}

fun navigateBack(): Boolean {
    return Store.instance.state.navigation.navigateBack
}

/*
class BackCallback(enabled: Boolean) : OnBackPressedCallback(enabled) {
    override fun handleOnBackPressed() {
        TODO("Not yet implemented")
    }


}

fun onDestinationChanged(
    controller: NavController,
    destination: NavDestination,
    arguments: Bundle?
): NavController.OnDestinationChangedListener {

}

 */