package ch.levelup.kaesseli.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch.levelup.kaesseli.KtorTestRepository
import kotlinx.coroutines.launch

@Composable
fun NetworkTestScreen(
) {
    Column(modifier = Modifier.padding(50.dp)) {

        val scope = rememberCoroutineScope()
        var text by remember { mutableStateOf("Loading") }
        LaunchedEffect(true) {
            scope.launch {
                text = try {
                    KtorTestRepository().greeting()
                } catch (e: Exception) {
                    e.localizedMessage ?: "error"
                }
            }
        }

        Text(
            text = text,
            style = TextStyle(fontSize = 21.sp)
        )
    }
}