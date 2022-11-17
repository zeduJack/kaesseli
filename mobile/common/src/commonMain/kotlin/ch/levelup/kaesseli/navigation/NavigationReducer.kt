package ch.levelup.kaesseli.navigation

fun navigationReducer(state: Navigation, action: Any) =
    when (action) {
        is NavigationActions.SetNavigation -> action.navigation
        else -> state
    }