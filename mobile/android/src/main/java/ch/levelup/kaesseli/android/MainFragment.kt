package ch.levelup.kaesseli.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import ch.levelup.kaesseli.android.ui.theme.JetpackComposeTestTheme

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                JetpackComposeTestTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        Column(
                            Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = greet())
                            Button(
                                onClick = { /*TODO*/ },
                                // Uses ButtonDefaults.ContentPadding by default
                                contentPadding = PaddingValues(
                                    start = 20.dp,
                                    top = 12.dp,
                                    end = 20.dp,
                                    bottom = 12.dp
                                )
                            ) {
                                // Inner content including an icon and a text label
                                Icon(
                                    Icons.Filled.Favorite,
                                    contentDescription = "Favorite",
                                    modifier = Modifier.size(ButtonDefaults.IconSize)
                                )
                                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                                Text("Like")
                            }
                            Button(onClick = { findNavController().navigate(R.id.codeSubmission) }) {
                                Text(text = "Code submission")
                            }
                            Button(onClick = { findNavController().navigate(R.id.register) }) {
                                Text(text = "Register")
                            }
                            Button(onClick = { findNavController().navigate(R.id.overview) }) {
                                Text(text = "Overview")
                            }
                            Button(onClick = { findNavController().navigate(R.id.balance) }) {
                                Text(text = "Balance")
                            }
                        }
                    }
                }
            }
        }
    }
}
