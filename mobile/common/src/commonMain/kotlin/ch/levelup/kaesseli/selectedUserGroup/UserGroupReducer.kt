package ch.levelup.kaesseli.selectedUserGroup

import ch.levelup.kaesseli.shared.LogedInUserUserGroupDto


fun userGroupReducer(state: LogedInUserUserGroupDto, action: Any) =
    when (action) {
        is UserGroupActions.SetSelectedGroup -> action.group
        else -> state
    }
