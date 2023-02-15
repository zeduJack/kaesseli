package ch.levelup.kaesseli.android.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun KsH1(text: String){
        Text(
            text = text,
            style = TextStyle(fontSize = 30.sp)
        )
}