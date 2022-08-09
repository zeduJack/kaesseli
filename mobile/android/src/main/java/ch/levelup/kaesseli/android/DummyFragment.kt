package ch.levelup.kaesseli.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class DummyFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply{
            setContent {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "DUMMY FRAGMENT",
                        style = TextStyle(fontSize = 21.sp)
                    )
                    Button(onClick = { findNavController().navigate(R.id.action_dummyFragment_to_dummy2Fragment) }) {
                        Text(text = "go to dummy-2")
                    }
                    Button(onClick = { findNavController().navigate(R.id.mainFragment) }) {
                        Text(text = "go directly to main")
                    }
                    Button(onClick = { findNavController().navigate(R.id.action_dummyFragment_to_mainFragment) }) {
                        Text(text = "go via action to main")
                    }
                }
            }
        }
    }
}