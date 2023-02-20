package ch.levelup.kaesseli.selectedUserGroup

import ch.levelup.kaesseli.shared.LogedInUserUserGroupDto


class UserGroupActions {
    data class SetSelectedGroup(val group: LogedInUserUserGroupDto)
}
