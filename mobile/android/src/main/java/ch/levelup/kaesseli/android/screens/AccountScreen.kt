package ch.levelup.kaesseli.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch.levelup.kaesseli.state.AppState

@Composable
fun AccountScreen(appState: AppState) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = appState.account.displayName,
            style = TextStyle(fontSize = 18.sp),
            modifier = Modifier.padding(0.dp, 18.dp)
        )
        Text(
            text = "Kontostand: " + appState.account.saldo,
            style = TextStyle(fontSize = 18.sp)
        )
    }
}