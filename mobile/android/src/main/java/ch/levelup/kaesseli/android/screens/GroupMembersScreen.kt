package ch.levelup.kaesseli.android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            text = "Übersicht - Mitglieder",
            style = TextStyle(fontSize = 18.sp)
        )

        val listModifier = Modifier
            .background(Color.Gray)
            .padding(10.dp)

        val textStyle = TextStyle(fontSize = 20.sp, color = Color.White)

        LazyColumn(modifier = listModifier) {

            items(appState.groupMembers) { member ->
                Text(text = member, style = textStyle)
            }
        }
/*
        Button(onClick = onNavigateToBalance) {
            // TODO set current group member, route to balance of selected member
            //Store.instance.dispatch(UserActions.SetGroupMember(memberName = "Melanie"))
            //Store.instance.dispatch(NavigationActions.SetNavigation(ScreenNavigation.BalanceScreen.route))
        }
*/
        Button(onClick = { Store.instance.dispatch(CreateUserGroup(userGroupName = "Neues Mitglied")) }) {
            Text(text = "Neues Mitglied hinzufügen")
        }
    }
}