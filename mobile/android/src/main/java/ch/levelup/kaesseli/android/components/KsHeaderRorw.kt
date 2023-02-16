package ch.levelup.kaesseli.android.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun KsHeaderRorw(content: @Composable RowScope.() -> Unit){

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 5.dp)

    ) {
        content()
    }
}