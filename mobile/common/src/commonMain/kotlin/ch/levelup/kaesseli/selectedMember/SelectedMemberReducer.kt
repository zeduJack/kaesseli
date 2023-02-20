package ch.levelup.kaesseli.selectedMember

import ch.levelup.kaesseli.shared.UserGroupMemberDto


fun memberReducer(state: UserGroupMemberDto, action: Any) =
    when (action) {
        is SelectedMemberActions.SetSelectedMember -> action.userGroupMemberDto
        else -> state
    }
