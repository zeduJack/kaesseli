package ch.levelup.kaesseli.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch.levelup.kaesseli.state.AddCountry
import ch.levelup.kaesseli.state.AppState
import ch.levelup.kaesseli.state.SetString
import ch.levelup.kaesseli.android.Store

@Composable
fun RecompositionTestScreen(
    appState: AppState
) {
    Column(modifier = Modifier.padding(50.dp)) {
        Text(
            text = appState.stringUser,
            style = TextStyle(fontSize = 21.sp)
        )

        Button(onClick = { Store.instance.dispatch(SetString("test")) }) {
            Text(text = "set string")
        }
        Button(onClick = { Store.instance.dispatch(AddCountry("test")) }) {
            Text(text = "add country")
        }
    }
}