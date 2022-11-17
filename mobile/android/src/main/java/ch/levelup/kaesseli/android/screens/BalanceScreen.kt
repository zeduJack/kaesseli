package ch.levelup.kaesseli.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BalanceScreen(){
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Kontostand von ",
            style = TextStyle(fontSize = 18.sp)
        )
    }
}