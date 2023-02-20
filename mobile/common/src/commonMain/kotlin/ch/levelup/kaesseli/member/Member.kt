package ch.levelup.kaesseli.member

import ch.levelup.kaesseli.shared.UserGroupMemberDto

data class Member(
    val selectedMember: UserGroupMemberDto = UserGroupMemberDto(),
    val title: String = ""
)

