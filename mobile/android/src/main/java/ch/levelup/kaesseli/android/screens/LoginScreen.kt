package ch.levelup.kaesseli.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch.levelup.kaesseli.android.Store
import ch.levelup.kaesseli.android.navigation.ScreenNavigation
import ch.levelup.kaesseli.navigation.NavigationActions
import ch.levelup.kaesseli.user.UserActions

@Composable
fun LoginScreen(
) {
    Column(modifier = Modifier.padding(50.dp)) {
        Text(
            text = "Bitte Benutzernamen eingeben",
            style = TextStyle(fontSize = 21.sp)
        )

        var username by remember { mutableStateOf(TextFieldValue("")) }
        var dirty by remember { mutableStateOf(false) }
        var emailValid by remember { mutableStateOf(false) }

        TextField(
            value = username,
            isError = dirty && !emailValid,
            label = { Text(text = "Benutzername") },
            onValueChange = { newText ->
                username = newText
                emailValid = isEmailValid(newText.text)
            }
        )
        if (dirty && !emailValid) {
            Text(
                text = "Die E-Mail Adresse ist ungültig",
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption
            )
        }

        Button(onClick = {
            dirty = true
            if (emailValid) {
                Store.instance.dispatch(UserActions.LogInUser(username = username.text))
                Store.instance.dispatch(NavigationActions.SetNavigation(ScreenNavigation.MainScreen.route))
            }
        }) {
            Text(text = "Anmelden")
        }
    }
}

fun isEmailValid(newValue: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(newValue).matches();
}

