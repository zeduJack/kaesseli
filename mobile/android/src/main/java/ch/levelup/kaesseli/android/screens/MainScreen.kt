package ch.levelup.kaesseli.android.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ch.levelup.kaesseli.android.greet

@Composable
fun MainScreen(
    onNavigateToCodeSubmission: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToOverview: () -> Unit,
    onNavigateToBalance: () -> Unit,

    ) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = greet())
        Button(
            onClick = { /*TODO*/ },
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
    }
}