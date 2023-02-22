package ch.levelup.kaesseli.android.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import ch.levelup.kaesseli.ScreenNavigation
import ch.levelup.kaesseli.navigation.Navigation
import ch.levelup.kaesseli.navigation.NavigationActions
import ch.levelup.kaesseli.state.AppState
import ch.levelup.kaesseli.state.Store

@Composable
fun KsProfileButton(appState: AppState){
    FloatingActionButton(
        onClick = { navigateToProfileOrLoginScreen(appState)},
        backgroundColor = MaterialTheme.colors.primaryVariant
    ) {
        Icon(
            Icons.Filled.Person,
            contentDescription = "Profile"
        )
    }
}

fun navigateToProfileOrLoginScreen(appState: AppState): Unit {
    if(appState.logedInUser.id == -1L){
        Store.instance.dispatch(NavigationActions.SetNavigation(Navigation(ScreenNavigation.LoginScreen.route)))
    } else {
        Store.instance.dispatch(NavigationActions.SetNavigation(Navigation(ScreenNavigation.ProfileScreen.route)))
    }
}