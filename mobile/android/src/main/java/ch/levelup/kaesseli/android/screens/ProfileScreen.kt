package ch.levelup.kaesseli.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ch.levelup.kaesseli.ScreenNavigation
import ch.levelup.kaesseli.navigation.Navigation
import ch.levelup.kaesseli.navigation.NavigationActions
import ch.levelup.kaesseli.shared.LogedInUserDto
import ch.levelup.kaesseli.state.Store
import ch.levelup.kaesseli.user.UserActions


@Composable
fun ProfileScreen() {

    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = {
            logOut()
        }) {
            Text(text = "Abmelden")
        }
    }
}

fun logOut() {
    Store.instance.dispatch(
        UserActions.SetUserData(
            LogedInUserDto()
        )
    )
    Store.instance.dispatch(NavigationActions.SetNavigation(Navigation(
        route = ScreenNavigation.LoginScreen.route,
        navigateBack = false,
        setAsFirstInBackStack = true)))
}
