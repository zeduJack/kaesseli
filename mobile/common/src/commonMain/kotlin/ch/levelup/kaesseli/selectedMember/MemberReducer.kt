package ch.levelup.kaesseli.selectedMember

import ch.levelup.kaesseli.user.MemberDto


fun memberReducer(state: MemberDto, action: Any) =
    when (action) {
        is SelectedMemberActions.SetSelectedMember -> action.member
        else -> state
    }
