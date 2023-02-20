package ch.levelup.kaesseli.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ch.levelup.kaesseli.ScreenNavigation
import ch.levelup.kaesseli.android.components.KsH1
import ch.levelup.kaesseli.android.components.KsHeaderRorw
import ch.levelup.kaesseli.android.components.KsListItem
import ch.levelup.kaesseli.android.components.KsPlusButton
import ch.levelup.kaesseli.android.listModifier
import ch.levelup.kaesseli.navigation.Navigation
import ch.levelup.kaesseli.navigation.NavigationActions
import ch.levelup.kaesseli.selectedUserGroup.UserGroupActions
import ch.levelup.kaesseli.shared.LogedInUserUserGroupDto
import ch.levelup.kaesseli.state.AppState
import ch.levelup.kaesseli.state.CreateUserGroup
import ch.levelup.kaesseli.state.Store

@Composable
fun StartScreen(
    appState: AppState
) {
    Column(modifier = Modifier.padding(10.dp)) {
        KsHeaderRorw {
            KsH1(text = appState.logedInUser.groupLabel)
            KsPlusButton(onClick = { Store.instance.dispatch(CreateUserGroup(userGroupName = "Neuer Gruppenname")) })
        }

        LazyColumn(modifier = listModifier) {
            items(appState.logedInUser.groups.toList()) { group: LogedInUserUserGroupDto ->
                KsListItem(
                    text = group.name,
                    onClick = {setSelectedGroup(group)},
                    detailText = group.membersDescription
                )
            }
        }
    }
}

private fun setSelectedGroup(selectedGroup: LogedInUserUserGroupDto){
    Store.instance.dispatch(UserGroupActions.SetSelectedGroup(selectedGroup))
    Store.instance.dispatch(NavigationActions.SetNavigation(Navigation(ScreenNavigation.GroupMembersScreen.route)))
}