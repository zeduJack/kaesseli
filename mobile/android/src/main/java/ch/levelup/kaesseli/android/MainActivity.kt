package ch.levelup.kaesseli.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ch.levelup.kaesseli.Greeting

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

