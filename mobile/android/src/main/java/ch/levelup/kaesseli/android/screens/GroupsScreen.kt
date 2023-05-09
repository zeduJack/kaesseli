package ch.levelup.kaesseli.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ch.levelup.kaesseli.ScreenNavigation
import ch.levelup.kaesseli.android.components.KsListItem
import ch.levelup.kaesseli.android.listModifier
import ch.levelup.kaesseli.navigation.Navigation
import ch.levelup.kaesseli.navigation.NavigationActions
import ch.levelup.kaesseli.permissions.PermissionsNetworkThunks
import ch.levelup.kaesseli.selectedMember.SelectedMemberActions
import ch.levelup.kaesseli.selectedUserGroup.UserGroupActions
import ch.levelup.kaesseli.shared.LogedInUserUserGroupDto
import ch.levelup.kaesseli.state.AppState
import ch.levelup.kaesseli.state.Store

@Composable
fun GroupsScreen(
    appState: AppState,
) {
    LaunchedEffect(true) {
        Store.instance.dispatch(PermissionsNetworkThunks.getPermissions())
    }
    Column(modifier = Modifier.padding(10.dp)) {
        LazyColumn(modifier = listModifier) {
            items(appState.logedInUser.groups.toList()) { group: LogedInUserUserGroupDto ->
                KsListItem(
                    text = group.name,
                    onClick = { setSelectedGroup(group, appState) },
                    detailText = group.membersDescription
                )
            }
        }
    }
}

private fun setSelectedGroup(selectedGroup: LogedInUserUserGroupDto, appState: AppState) {
    Store.instance.dispatch(UserGroupActions.SetSelectedGroup(selectedGroup))
    if (appState.permissions.seeOtherGroupMembersAllowed) {
        Store.instance.dispatch(NavigationActions.SetNavigation(Navigation(ScreenNavigation.GroupMembersScreen.route)))
    } else {
        val first = selectedGroup.members.first { it.username == appState.logedInUser.username }
        Store.instance.dispatch(SelectedMemberActions.SetSelectedMember(first))
        Store.instance.dispatch(NavigationActions.SetNavigation(Navigation(ScreenNavigation.MemberScreen.route)))
    }
}
