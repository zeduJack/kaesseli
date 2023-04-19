package ch.levelup.kaesseli.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ch.levelup.kaesseli.ScreenNavigation
import ch.levelup.kaesseli.android.components.KsListItem
import ch.levelup.kaesseli.android.listModifier
import ch.levelup.kaesseli.navigation.Navigation
import ch.levelup.kaesseli.navigation.NavigationActions
import ch.levelup.kaesseli.selectedUserGroup.UserGroupActions
import ch.levelup.kaesseli.shared.LogedInUserUserGroupDto
import ch.levelup.kaesseli.state.AppState
import ch.levelup.kaesseli.state.Store

@Composable
fun GroupsScreen(
    appState: AppState
) {
    Column(modifier = Modifier.padding(10.dp)) {
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