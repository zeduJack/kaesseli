package ch.levelup.kaesseli.errorMessage

fun errorMessageReducer(state: String?, action: Any) =
    when (action) {
        is ErrorMessageActions.SetErrorMessage -> action.message
        is ErrorMessageActions.ClearErrorMessage -> ""
        else -> state
    }