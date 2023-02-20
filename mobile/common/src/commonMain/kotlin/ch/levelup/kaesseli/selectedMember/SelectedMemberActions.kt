package ch.levelup.kaesseli.selectedMember

import ch.levelup.kaesseli.shared.UserGroupMemberDto


class SelectedMemberActions {
    data class SetSelectedMember(val userGroupMemberDto: UserGroupMemberDto)
}
