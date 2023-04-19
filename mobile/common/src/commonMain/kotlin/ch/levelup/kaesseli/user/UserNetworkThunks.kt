package ch.levelup.kaesseli.user

import ch.levelup.kaesseli.GatewayResponse
import ch.levelup.kaesseli.GenericError
import ch.levelup.kaesseli.PlatformDispatcher
import ch.levelup.kaesseli.ScreenNavigation
import ch.levelup.kaesseli.errorMessage.ErrorMessageActions
import ch.levelup.kaesseli.fetchingData.FetchingDataActions
import ch.levelup.kaesseli.login.LoginActions
import ch.levelup.kaesseli.login.LoginInput
import ch.levelup.kaesseli.navigation.Navigation
import ch.levelup.kaesseli.navigation.NavigationActions
import ch.levelup.kaesseli.scaffold.ScafffoldActions
import ch.levelup.kaesseli.shared.LogedInUserDto
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

    fun logInUser(): Thunk<AppState> = { dispatch, getState, extraArg ->
        var response: GatewayResponse<LogedInUserDto, GenericError>?
        dispatch(ErrorMessageActions.ClearErrorMessage)
        dispatch(FetchingDataActions.StartNetworkRequest)

        val email = getState().login.username

        networkScope.launch(tunkExceptionHandler(dispatch)) {
            response = userRepository.logInUser(email)

            dispatch(FetchingDataActions.EndNetworkRequest)
            if (response != null) {
                if (response!!.isFailure) {
                    dispatch(ErrorMessageActions.SetErrorMessage(response!!.errorResponse?.message))
                } else {
                    if (response?.response != null) {
                        dispatch(UserActions.SetUser(response!!.response!!))
                        dispatch(LoginActions.ChangeLogin(LoginInput(username = "", isDirty = false)))
                    }
                    dispatch(NavigationActions.SetNavigation(Navigation(route = ScreenNavigation.GroupsScreen.route)))
                    dispatch(ScafffoldActions.SetTobBarTitle(getState()))
                }
            }
        }
    }
}
