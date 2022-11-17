package ch.levelup.kaesseli.welcomeMessage

import ch.levelup.kaesseli.user.UserActions

fun welcomeMessageReducer(state: WelcomeMessage, action: Any) =
    when (action) {
        is UserActions.SetUser -> updateMessage(action.user.firstname)
        else -> state
    }

fun updateMessage(name: String): WelcomeMessage {
    if (name != "") {
        return WelcomeMessage("Willkommen, ${name}")
    } else {
        return WelcomeMessage("Hallo, bitte melde Dich an")
    }
}
