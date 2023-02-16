package ch.levelup.kaesseli.groupView

import ch.levelup.kaesseli.user.UserActions
import ch.levelup.kaesseli.user.UserDto


fun groupViewReducer(state: GroupView, action: Any) =
    when (action) {
        is UserActions.SetUser -> getGroups(action.user, state)
        else -> state
    }

fun getGroups(userDto: UserDto, state: GroupView): GroupView {

    return GroupView(
        title = state.title,
        groups = userDto.userGroups.map { group ->
            Group(
                userGroupDto = group,
                description = group.members.size.toString() + " Mitglieder"
            )
        }.toSet()
    )
}
