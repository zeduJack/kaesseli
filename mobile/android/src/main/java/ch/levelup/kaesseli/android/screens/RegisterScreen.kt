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

@Composable
fun RegisterScreen(
    onNavigateToOverview: () -> Unit
) {
    Column(modifier = Modifier.padding(50.dp)) {
        Text(
            text = "CREATE ACCOUNT",
            style = TextStyle(fontSize = 21.sp)
        )

        var name by remember { mutableStateOf(TextFieldValue("")) }
        TextField(
            value = name,
            label = { Text(text = "Name") },
            onValueChange = { newText ->
                name = newText
            }
        )

        var email by remember { mutableStateOf(TextFieldValue("")) }
        TextField(
            value = email,
            label = { Text(text = "Email") },
            onValueChange = { newText ->
                email = newText
            }
        )

        Button(onClick = onNavigateToOverview) {
            Text(text = "Submit")
        }
    }
}