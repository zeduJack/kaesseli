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
fun CodeSubmissionScreen(
    onNavigateToOverview: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    Column(modifier = Modifier.padding(50.dp)) {
        Text(
            text = "INVITATION CODE",
            style = TextStyle(fontSize = 21.sp)
        )

        var text by remember { mutableStateOf(TextFieldValue("")) }
        TextField(
            value = text,
            label = { Text(text = "code") },
            onValueChange = { newText ->
                text = newText
            }
        )

        Button(onClick = onNavigateToOverview) {
            Text(text = "Submit")
        }

        Button(onClick = onNavigateToRegister) {
            Text(text = "Create Account")
        }
    }
}