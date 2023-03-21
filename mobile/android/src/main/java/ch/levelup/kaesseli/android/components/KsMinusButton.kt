package ch.levelup.kaesseli.android.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable

@Composable
fun KsMinusButton(onClick: () -> Unit){
    FloatingActionButton(
        onClick = { onClick()},
        backgroundColor = MaterialTheme.colors.primaryVariant
    ) {
        Icon(
            Icons.Filled.Edit,
            contentDescription = "Minus"
        )
    }
}