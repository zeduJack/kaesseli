package ch.levelup.kaesseli.welcomeMessage

import ch.levelup.kaesseli.user.User
import ch.levelup.kaesseli.user.UserActions

fun welcomeMessageReducer(state: String, action: Any) =
    when (action) {
        is UserActions.SetUser -> {
            if(action.user.id == User.defaultUserId){
                "Bitte loggen sie sich ein"
            } else {
            "Willkommen ${action.user.firstname}"
            }
        }
        else -> state
    }