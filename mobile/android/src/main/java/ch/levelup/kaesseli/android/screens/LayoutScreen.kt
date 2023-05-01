package ch.levelup.kaesseli.android.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch.levelup.kaesseli.ScreenNavigation
import ch.levelup.kaesseli.navigation.Navigation
import ch.levelup.kaesseli.navigation.NavigationActions
import ch.levelup.kaesseli.permissions.PermissionsNetworkThunks
import ch.levelup.kaesseli.state.AppState
import ch.levelup.kaesseli.state.Store

@Composable
fun LayoutScreen(
    appState: AppState,
    onFloatingActionButtonClick: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    LaunchedEffect(true) {
        Store.instance.dispatch(PermissionsNetworkThunks.getPermissions())
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp, 5.dp, 5.dp, 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = appState.scaffold.topBar.title)
                    }
                },
                backgroundColor = MaterialTheme.colors.primary
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            if (appState.permissions.executeTranactionsAllowed) {
                FloatingActionButton(onClick = {
                    if (onFloatingActionButtonClick != null) {
                        onFloatingActionButtonClick()
                    }
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "fab icon")
                }
            }
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    if (appState.fetchingData) {
                        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                    }
                }

                Row(modifier = Modifier.fillMaxWidth()) {
                    if (appState.errorMessage != null && appState.errorMessage != "") {
                        Text(
                            text = appState.errorMessage!!,
                            color = Color.Red,
                            fontSize = 20.sp
                        )
                    }
                }
                content()
            }
        },
        bottomBar = { BottomBar(appState) }
    )
}

@Composable
fun BottomBar(appState: AppState) {
    val selectedIndex = remember { mutableStateOf(0) }
    BottomNavigation(elevation = 10.dp) {

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Home, "")
        },
            label = { Text(text = "Home") },
            selected = (selectedIndex.value == 0),
            onClick = {
                selectedIndex.value = 0
            })

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Person, "")
        },
            label = { Text(text = "Profil") },
            selected = (selectedIndex.value == 2),
            onClick = {
                navigateToProfileOrLoginScreen(appState)
            })
    }
}

fun navigateToProfileOrLoginScreen(appState: AppState) {
    if (appState.logedInUser.id == -1L) {
        Store.instance.dispatch(NavigationActions.SetNavigation(Navigation(ScreenNavigation.LoginScreen.route)))
    } else {
        Store.instance.dispatch(NavigationActions.SetNavigation(Navigation(ScreenNavigation.ProfileScreen.route)))
    }
}
