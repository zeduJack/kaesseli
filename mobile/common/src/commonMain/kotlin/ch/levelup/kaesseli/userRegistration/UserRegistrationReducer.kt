package ch.levelup.kaesseli.userRegistration

fun userRegistrationReducer(state: UserRegistration?, action: Any) =
    when (action) {
        is UserRegistrationActions.RegisterUser -> action.userRegistration
        is UserRegistrationActions.ClearUserRegistration -> null
        else -> state
    }
