package ch.levelup.kaesseli.user

import ch.levelup.kaesseli.PlatformDispatcher
import ch.levelup.kaesseli.errorMessage.ErrorMessageActions
import ch.levelup.kaesseli.fetchingData.FetchingDataActions
import ch.levelup.kaesseli.state.AppState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.reduxkotlin.Dispatcher
import org.reduxkotlin.Thunk

fun tunkExceptionHandler(dispatch: Dispatcher): CoroutineExceptionHandler {
    return CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
        dispatch(ErrorMessageActions.SetErrorMessage("Error in UserNetworkThunks"))
        dispatch(FetchingDataActions.EndNetworkRequest)
    }
}

/**
 * Thunks are functions that are executed by the "ThunkMiddleware".  They are asynchronous and dispatch
 * actions.  This allows dispatching a loading, success, and failure state.
 */
object UserNetworkThunks {
    private val userRepository = UserRepository()
    private val networkScope = CoroutineScope(PlatformDispatcher)

    fun createUser(user: User?): Thunk<AppState> = { dispatch, getState, extraArg ->
        networkScope.launch(tunkExceptionHandler(dispatch)) {
            dispatch(ErrorMessageActions.ClearErrorMessage)
            dispatch(FetchingDataActions.StartNetworkRequest)
            val response = userRepository.createUser(user = user)
            dispatch(FetchingDataActions.EndNetworkRequest)

            if (response.isFailure) {
                dispatch(ErrorMessageActions.SetErrorMessage(response.errorResponse?.message))
            } else {
                dispatch(UserActions.UserCreated(response.response))
            }
            //throw Exception("test") // is for testing tunkExceptionHandler
        }
    }
}
