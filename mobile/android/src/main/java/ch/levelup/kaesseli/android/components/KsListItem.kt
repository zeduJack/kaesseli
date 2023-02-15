package ch.levelup.kaesseli.android.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun KsListItem(text: String, onClick: () -> Unit){

    val listItemModifier = Modifier
        .padding(1.dp)
        .clip(shape = RoundedCornerShape(20.dp))
        .background(Color(0, 60, 180))
        .padding(0.dp, 10.dp, 0.dp, 10.dp)

    val textColor = Color.White

    ListItem(
        modifier = listItemModifier.clickable { onClick() },
        text = { Text(text = text, color = textColor, fontSize = 5.em) },
        trailing = {
            Icon(
                Icons.Filled.PlayArrow,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = Color.White
            )
        }
    )
}