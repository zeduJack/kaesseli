package ch.levelup.kaesseli.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import ch.levelup.kaesseli.Greeting
import ch.levelup.kaesseli.android.navigation.Navigation
import ch.levelup.kaesseli.android.navigation.Screen
import ch.levelup.kaesseli.android.ui.theme.JetpackComposeTestTheme

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var startDestination by remember { mutableStateOf(Screen.MainScreen.route) }
            if (false) {
                startDestination = Screen.RegisterScreen.route
            }

            JetpackComposeTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation(startDestination)
                }
            }
        }
    }
}


