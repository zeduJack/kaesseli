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
import ch.levelup.kaesseli.state.Store
import ch.levelup.kaesseli.ScreenNavigation
import ch.levelup.kaesseli.navigation.Navigation
import ch.levelup.kaesseli.navigation.NavigationActions
import ch.levelup.kaesseli.selectedMember.SelectedMemberActions
import ch.levelup.kaesseli.state.CreateUserGroup
import ch.levelup.kaesseli.user.MemberDto

@Composable
fun GroupMembersScreen(
    appState: AppState
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Gruppenmitglieder",
            style = TextStyle(fontSize = 18.sp)
        )

        val listModifier = Modifier
            .background(Color.White)
            .padding(10.dp)

        //val textStyle = TextStyle(fontSize = 20.sp, color = Color.White)
        val context = LocalContext.current
        LazyColumn(modifier = listModifier) {
            items(appState.userGroup.members.toList()) { member: MemberDto ->
                TextButton(modifier = Modifier.fillMaxWidth(), onClick = { setSelectedMember(member, context)}) {
                    Row {
                        Text(member.firstname)
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

private fun setSelectedMember(selectedMember: MemberDto, context: Context){
    Toast.makeText(context, selectedMember.firstname, Toast.LENGTH_LONG).show()
    Store.instance.dispatch(SelectedMemberActions.SetSelectedMember(selectedMember))
    Store.instance.dispatch(NavigationActions.SetNavigation(Navigation(ScreenNavigation.MemberScreen.route)))
}