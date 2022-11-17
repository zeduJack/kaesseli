package ch.levelup.kaesseli.user

fun userReducer(state: UserDto, action: Any) =
    when (action) {
        is UserActions.SetUser -> action.user
        else -> state
    }