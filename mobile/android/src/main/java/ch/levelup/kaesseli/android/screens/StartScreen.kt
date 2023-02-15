package ch.levelup.kaesseli.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch.levelup.kaesseli.ScreenNavigation
import ch.levelup.kaesseli.android.components.KsH1
import ch.levelup.kaesseli.android.components.KsHeadderRorw
import ch.levelup.kaesseli.android.components.KsListItem
import ch.levelup.kaesseli.android.components.KsPlusButton
import ch.levelup.kaesseli.android.listModifier
import ch.levelup.kaesseli.navigation.Navigation
import ch.levelup.kaesseli.navigation.NavigationActions
import ch.levelup.kaesseli.selectedUserGroup.UserGroupActions
import ch.levelup.kaesseli.state.AppState
import ch.levelup.kaesseli.state.CreateUserGroup
import ch.levelup.kaesseli.state.Store
import ch.levelup.kaesseli.user.UserGroupDto

@Composable
fun StartScreen(
    appState: AppState
) {
    Column(modifier = Modifier.padding(10.dp)) {
        KsHeadderRorw {
            KsH1(text = "Gruppen")
            KsPlusButton(onClick = { Store.instance.dispatch(CreateUserGroup(userGroupName = "Neuer Gruppenname")) })
        }

        LazyColumn(modifier = listModifier) {
            items(appState.user.userGroups.toList()) { group: UserGroupDto ->
                KsListItem(text = group.name, onClick = {setSelectedGroup(group)})
            }
        }
    }
}

private fun setSelectedGroup(selectedGroup: UserGroupDto){
    Store.instance.dispatch(UserGroupActions.SetSelectedGroup(selectedGroup))
    Store.instance.dispatch(NavigationActions.SetNavigation(Navigation(ScreenNavigation.GroupMembersScreen.route)))
}