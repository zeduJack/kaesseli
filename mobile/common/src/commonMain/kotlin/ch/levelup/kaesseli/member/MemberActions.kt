package ch.levelup.kaesseli.member

import ch.levelup.kaesseli.user.MemberDto

class SelectedMemberActions {
    data class SetSelectedMember(val memberDto: MemberDto)
}
