package ch.levelup.kaesseli.user

fun userReducer(state: User, action: Any) =
    when (action) {
        is UserActions.SetUser -> action.user
        else -> state
    }