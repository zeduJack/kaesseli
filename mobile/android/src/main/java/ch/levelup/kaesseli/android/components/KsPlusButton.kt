package ch.levelup.kaesseli.android.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable

@Composable
fun KsPlusButton(onClick: () -> Unit){
    FloatingActionButton(
        onClick = { onClick()},
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Add")
    }
}