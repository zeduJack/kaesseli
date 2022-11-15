package ch.levelup.kaesseli.android.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import ch.levelup.kaesseli.state.AppState

@Composable
fun LayoutScreen(
    appState: AppState,
    content: @Composable () -> Unit
) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            if (appState.fetchingData) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            if (appState.errorMessage != null && appState.errorMessage != "") {
                Text(
                    text = appState.errorMessage!!,
                    color = Color.Red,
                    fontSize = 20.sp
                )
            }
        }

        Text(text = appState.welcomeMessage)

        content()
    }
}