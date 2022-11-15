package ch.levelup.kaesseli.navigation

fun navigationReducer(state: String, action: Any) =
    when (action) {
        is NavigationActions.SetNavigation -> action.route
        else -> state
    }