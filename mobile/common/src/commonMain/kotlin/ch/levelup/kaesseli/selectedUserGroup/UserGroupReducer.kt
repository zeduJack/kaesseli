package ch.levelup.kaesseli.selectedUserGroup

import ch.levelup.kaesseli.user.UserGroupDto


fun userGroupReducer(state: UserGroupDto, action: Any) =
    when (action) {
        is UserGroupActions.SetSelectedGroup -> action.group
        else -> state
    }
