package ch.levelup.kaesseli.user

import ch.levelup.kaesseli.shared.LogedInUserDto

fun userReducer(state: LogedInUserDto, action: Any) =
    when (action) {
        is UserActions.SetUserData -> action.user
        else -> state
    }
