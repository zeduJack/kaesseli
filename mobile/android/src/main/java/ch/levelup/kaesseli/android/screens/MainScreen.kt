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
import ch.levelup.kaesseli.android.greet
import ch.levelup.kaesseli.state.AppState
import ch.levelup.kaesseli.state.SetString
import ch.levelup.kaesseli.user.User
import ch.levelup.kaesseli.user.UserNetworkThunks

@Composable
fun MainScreen(
    appState: AppState,
    onNavigateToCodeSubmission: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToOverview: () -> Unit,
    onNavigateToBalance: () -> Unit,
    onNavigateToRecompositionTest: () -> Unit,
    onNavigateToNetworkView: () -> Unit,

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
                fontSize = 30.sp
            )
        }
    }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = greet())
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
        Button(onClick = onNavigateToOverview) {
            Text(text = "Overview")
        }
        Button(onClick = onNavigateToBalance) {
            Text(text = "Balance")
        }
        Button(onClick = onNavigateToRecompositionTest) {
            Text(text = "Recompositon Test")
        }
        Button(onClick = {
            Store.instance.dispatch(
                UserNetworkThunks.createUser(
                    null
                )
            )
        }) {
            Text(text = "Do network request")
        }
        Button(onClick = onNavigateToNetworkView) {
            Text(text = "Network view")
        }
    }
}