package ch.levelup.kaesseli.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch.levelup.kaesseli.state.Store
import ch.levelup.kaesseli.login.LoginActions
import ch.levelup.kaesseli.login.LoginInput
import ch.levelup.kaesseli.state.AppState
import ch.levelup.kaesseli.user.UserNetworkThunks

@Composable
fun LoginScreen(appState: AppState) {
    Column(modifier = Modifier.padding(50.dp)) {
        Text(
            text = appState.login.loginTextPlaceholder,
            style = TextStyle(fontSize = 18.sp)
        )

        var dirty by remember { mutableStateOf(false) }

        TextField(
            value = appState.login.username,
            isError = appState.login.isDirty && !appState.login.isValid,
            label = { Text(text = appState.login.loginFieldLabel) },
            onValueChange = { newText -> dispatchNewState(newText, dirty) }
        )
        if (appState.login.isDirty && !appState.login.isValid) {
            Text(
                text = appState.login.loginInvalidMessage,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption
            )
        }

        Button(onClick = {
            dirty = true
            if (appState.login.isValid) {
                // send network request, verify username exists and is valid, store user account values inside state
                Store.instance.dispatch(UserNetworkThunks.logInUser())
            } else {
                dispatchNewState(appState.login.username, dirty)
            }

        }) {
            Text(text = appState.login.loginButtonText)
        }
    }
}

fun dispatchNewState(newText: String, dirty: Boolean): Unit {
    Store.instance.dispatch(
        LoginActions.ChangeLogin(
            LoginInput(
                username = newText,
                dirty
            )
        )
    )
}

