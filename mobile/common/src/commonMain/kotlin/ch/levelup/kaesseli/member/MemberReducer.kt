package ch.levelup.kaesseli.member

import ch.levelup.kaesseli.shared.UserGroupMemberDto


fun memberReducer(state: Member, action: Any) =
    when (action) {
        is SelectedMemberActions.SetSelectedMember -> getMember(action.userGroupMemberDto)
        else -> state
    }

fun getMember(memberDto: UserGroupMemberDto): Member {
   return Member(
        selectedMember = memberDto,
        title = "Konten von " + memberDto.firstname
    )
}
