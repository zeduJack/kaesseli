package ch.levelup.kaesseli.groupView

import ch.levelup.kaesseli.shared.LogedInUserDto
import ch.levelup.kaesseli.user.UserActions



fun groupViewReducer(state: GroupView, action: Any) =
    when (action) {
        is UserActions.SetUser -> getGroups(action.user, state)
        else -> state
    }

fun getGroups(userDto: LogedInUserDto, state: GroupView): GroupView {

    return GroupView(
        title = state.title,
        groups = userDto.groups.map { group ->
            Group(
                userGroupDto = group,
                description = group.members.size.toString() + " Mitglieder"
            )
        }.toSet()
    )
}
