package ch.levelup.kaesseli.member

import ch.levelup.kaesseli.shared.UserGroupMemberDto


class SelectedMemberActions {
    data class SetSelectedMember(val userGroupMemberDto: UserGroupMemberDto)
}
