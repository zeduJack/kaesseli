package ch.levelup.kaesseli.member

import ch.levelup.kaesseli.user.MemberDto


fun memberReducer(state: Member, action: Any) =
    when (action) {
        is SelectedMemberActions.SetSelectedMember -> getMember(action.memberDto)
        else -> state
    }

fun getMember(memberDto: MemberDto): Member {
   return Member(
        selectedMember = memberDto,
        title = "Konten von " + memberDto.firstname
    )
}
