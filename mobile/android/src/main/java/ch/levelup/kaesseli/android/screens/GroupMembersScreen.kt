package ch.levelup.kaesseli.android.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
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
fun GroupMembersScreen(
    appState: AppState
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Übersicht - Gruppen",
            style = TextStyle(fontSize = 18.sp)
        )

        val listModifier = Modifier
            .background(Color.White)
            .padding(10.dp)

        //val textStyle = TextStyle(fontSize = 20.sp, color = Color.White)
        val context = LocalContext.current
        LazyColumn(modifier = listModifier) {
            items(appState.groupMembers) { groupName: String ->
                TextButton(modifier = Modifier.fillMaxWidth(), onClick = { displayToast(groupName, context)}) {
                    Row {
                        Text(groupName)
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }

        Button(onClick = { Store.instance.dispatch(CreateUserGroup(userGroupName = "Neues Mitglied")) }) {
            Text(text = "Neues Mitglied hinzufügen")
        }
    }
}

private fun displayToast(groupName: String, context: Context){
    Toast.makeText(context, groupName, Toast.LENGTH_LONG).show()
    // TODO set member name, route to balance screen
    //Store.instance.dispatch(UserActions.SelectUserGroup(userGroupName = ""))
    Store.instance.dispatch(NavigationActions.SetNavigation(ScreenNavigation.BalanceScreen.route))
}