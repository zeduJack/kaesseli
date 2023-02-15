package ch.levelup.kaesseli.android.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ch.levelup.kaesseli.ScreenNavigation
import ch.levelup.kaesseli.android.components.KsH1
import ch.levelup.kaesseli.android.components.KsHeadderRorw
import ch.levelup.kaesseli.android.components.KsListItem
import ch.levelup.kaesseli.android.components.KsPlusButton
import ch.levelup.kaesseli.android.listModifier
import ch.levelup.kaesseli.navigation.Navigation
import ch.levelup.kaesseli.navigation.NavigationActions
import ch.levelup.kaesseli.member.SelectedMemberActions
import ch.levelup.kaesseli.state.AppState
import ch.levelup.kaesseli.state.CreateUserGroup
import ch.levelup.kaesseli.state.Store
import ch.levelup.kaesseli.user.MemberDto

@Composable
fun GroupMembersScreen(
    appState: AppState
) {
    Column(modifier = Modifier.padding(16.dp)) {
        KsHeadderRorw {
            KsH1(text = "Gruppenmitglieder")
            KsPlusButton(onClick = { Store.instance.dispatch(CreateUserGroup(userGroupName = "Neues Mitglied")) })
        }

        val context = LocalContext.current

        LazyColumn(modifier = listModifier) {
            items(appState.userGroup.members.toList()) { member: MemberDto ->
                KsListItem(text = member.firstname, onClick = {setSelectedMember(member, context)})
            }
        }
    }
}

private fun setSelectedMember(selectedMember: MemberDto, context: Context){
    Toast.makeText(context, selectedMember.firstname, Toast.LENGTH_LONG).show()
    Store.instance.dispatch(SelectedMemberActions.SetSelectedMember(selectedMember))
    Store.instance.dispatch(NavigationActions.SetNavigation(Navigation(ScreenNavigation.MemberScreen.route)))
}