package ch.levelup.kaesseli.android.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch.levelup.kaesseli.android.Store
import ch.levelup.kaesseli.state.AppState
import ch.levelup.kaesseli.state.SetString

@Composable
fun MainScreen(
    appState: AppState,
    onNavigateToCodeSubmission: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToBalance: () -> Unit,
    ) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
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

            Text(text = appState.welcomeMessage.message)

            Text(text = "Hello")
            Button(
                onClick = { Store.instance.dispatch(SetString("Button clicked")) },
                // Uses ButtonDefaults.ContentPadding by default
                contentPadding = PaddingValues(
                    start = 20.dp,
                    top = 12.dp,
                    end = 20.dp,
                    bottom = 12.dp
                )
            ) {
                // Inner content including an icon and a text label
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Favorite",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Like")
            }
            Button(onClick = onNavigateToCodeSubmission) {
                Text(text = "Code submission")
            }
            Button(onClick = onNavigateToRegister) {
                Text(text = "Register")
            }
            Button(onClick = onNavigateToBalance) {
                Text(text = "Balance")
            }
        }
    }
}