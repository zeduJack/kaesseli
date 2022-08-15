package ch.levelup.kaesseli.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class Register : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply{
            setContent {
                Column(modifier = Modifier.padding(50.dp)) {
                    Text(
                        text = "CREATE ACCOUNT",
                        style = TextStyle(fontSize = 21.sp)
                    )

                    var name by remember { mutableStateOf(TextFieldValue("")) }
                    TextField(
                        value = name,
                        label = { Text(text = "Name") },
                        onValueChange = { newText ->
                            name = newText
                        }
                    )

                    var email by remember { mutableStateOf(TextFieldValue("")) }
                    TextField(
                        value = email,
                        label = { Text(text = "Email") },
                        onValueChange = { newText ->
                            email = newText
                        }
                    )

                    Button(onClick = { findNavController().navigate(R.id.action_register_to_overview) }) {
                        Text(text = "Submit")
                    }



                }
            }
        }
    }
}