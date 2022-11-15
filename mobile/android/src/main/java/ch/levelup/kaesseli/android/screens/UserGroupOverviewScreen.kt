package ch.levelup.kaesseli.android.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch.levelup.kaesseli.state.AppState
import ch.levelup.kaesseli.android.Store
import ch.levelup.kaesseli.android.navigation.ScreenNavigation
import ch.levelup.kaesseli.navigation.NavigationActions
import ch.levelup.kaesseli.state.CreateUserGroup
import ch.levelup.kaesseli.user.UserActions

@Composable
fun UserGroupOverviewScreen(
    appState: AppState
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Übersicht - Gruppen",
            style = TextStyle(fontSize = 18.sp)
        )

        val listModifier = Modifier
            .background(Color.Gray)
            .padding(10.dp)

        val textStyle = TextStyle(fontSize = 20.sp, color = Color.White)
        val context = LocalContext.current
        LazyColumn(modifier = listModifier) {
            items(appState.userGroups) { groupName ->
                GreetingView(name = groupName) {
                    Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                }
            }
        }
/*
        Button(onClick = onNavigateToBalance) {
            // TODO set user group name, route to group overview
            Store.instance.dispatch(UserActions.SelectUserGroup(userGroupName = ""))
            Store.instance.dispatch(NavigationActions.SetNavigation(ScreenNavigation.GroupMemberOverview.route))
        }
*/
        Button(onClick = { Store.instance.dispatch(CreateUserGroup(userGroupName = "Neuer Gruppenname")) }) {
            Text(text = "Neue Gruppe erstellen")
        }
    }
}
@Composable
private fun GreetingView(name: String, onClick: (msg: String) -> Unit) {
    //val msg = "Hello, $name"

    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable { onClick("Hello, $name") }
    ) {
        Row(modifier = Modifier.padding(12.dp).fillMaxWidth()) {
            Text(text = name)
        }
    }
}