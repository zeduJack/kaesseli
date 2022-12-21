package ch.levelup.kaesseli.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch.levelup.kaesseli.userRegistration.UserRegistration
import ch.levelup.kaesseli.state.Store
import ch.levelup.kaesseli.userRegistration.UserRegistrationActions

@Composable
fun RegisterScreen(
    onNavigateToOverview: () -> Unit
) {
    Column(modifier = Modifier.padding(50.dp)) {
        Text(
            text = "Konto erstellen",
            style = TextStyle(fontSize = 18.sp)
        )

        var firstname by remember { mutableStateOf(TextFieldValue("")) }
        TextField(
            value = firstname,
            label = { Text(text = "Vorname") },
            onValueChange = { newText ->
                firstname = newText
            }
        )

        var lastname by remember { mutableStateOf(TextFieldValue("")) }
        TextField(
            value = lastname,
            label = { Text(text = "Nachname") },
            onValueChange = { newText ->
                lastname = newText
            }
        )

        var email by remember { mutableStateOf(TextFieldValue("")) }
        TextField(
            value = email,
            label = { Text(text = "E-Mail") },
            onValueChange = { newText ->
                email = newText
            }
        )

        Button(onClick = {
            Store.instance.dispatch(UserRegistrationActions.RegisterUser(UserRegistration(name = firstname.text, email = email.text, password = "hello world")))
            onNavigateToOverview()
        }) {
            Text(text = "Erstellen")
        }
    }
}