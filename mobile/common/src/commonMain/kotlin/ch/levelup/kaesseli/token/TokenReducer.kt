package ch.levelup.kaesseli.token



fun tokenReducer(state: String, action: Any) =
    when (action) {
        is TokenActions.SetToken -> action.token
        else -> state
    }