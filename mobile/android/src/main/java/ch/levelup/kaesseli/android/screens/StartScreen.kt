package ch.levelup.kaesseli.android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch.levelup.kaesseli.state.AppState
import ch.levelup.kaesseli.android.Store
import ch.levelup.kaesseli.ScreenNavigation
import ch.levelup.kaesseli.navigation.Navigation
import ch.levelup.kaesseli.navigation.NavigationActions
import ch.levelup.kaesseli.selectedUserGroup.UserGroupActions
import ch.levelup.kaesseli.state.CreateUserGroup
import ch.levelup.kaesseli.user.UserGroupDto

@Composable
fun StartScreen(
    appState: AppState
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Gruppen",
            style = TextStyle(fontSize = 18.sp)
        )

        val listModifier = Modifier
            .background(Color.White)
            .padding(10.dp)

        //val textStyle = TextStyle(fontSize = 20.sp, color = Color.White)
        val context = LocalContext.current
        LazyColumn(modifier = listModifier) {
            items(appState.user.userGroups) { group: UserGroupDto ->
                TextButton(modifier = Modifier.fillMaxWidth(), onClick = { setSelectedGroup(group)}) {
                    Row {
                        Text(group.name)
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }

        Button(onClick = { Store.instance.dispatch(CreateUserGroup(userGroupName = "Neuer Gruppenname")) }) {
            Text(text = "Neue Gruppe erstellen")
        }
    }
}

private fun setSelectedGroup(selectedGroup: UserGroupDto){
    Store.instance.dispatch(UserGroupActions.SetSelectedGroup(selectedGroup))
    Store.instance.dispatch(NavigationActions.SetNavigation(Navigation(ScreenNavigation.GroupMembersScreen.route)))
}