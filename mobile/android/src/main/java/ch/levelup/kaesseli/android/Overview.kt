package ch.levelup.kaesseli.android


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class Overview : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "OVERVIEW",
                        style = TextStyle(fontSize = 21.sp)
                    )

                    val countryList = mutableListOf("Child 1", "Child 2", "Child 3")

                    val listModifier = Modifier
                        .background(Color.Gray)
                        .padding(10.dp)

                    val textStyle = TextStyle(fontSize = 20.sp, color = Color.White)


                    LazyColumn(modifier = listModifier) {
                        items(countryList) { country ->
                            Text(text = country, style = textStyle)
                        }
                    }

                    Button(onClick = { findNavController().navigate(R.id.balance) }) {
                        Text(text = "balance")
                    }

                }
            }
        }
    }
}