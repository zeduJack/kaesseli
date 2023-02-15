package ch.levelup.kaesseli.member

import ch.levelup.kaesseli.user.MemberDto

data class Member(
    val selectedMember: MemberDto = MemberDto(),
    val title: String = ""
)

