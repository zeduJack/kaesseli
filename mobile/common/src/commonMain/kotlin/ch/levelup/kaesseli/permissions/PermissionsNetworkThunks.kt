package ch.levelup.kaesseli.permissions

import ch.levelup.kaesseli.GatewayResponse
import ch.levelup.kaesseli.GenericError
import ch.levelup.kaesseli.PlatformDispatcher
import ch.levelup.kaesseli.errorMessage.ErrorMessageActions
import ch.levelup.kaesseli.fetchingData.FetchingDataActions
import ch.levelup.kaesseli.shared.PermissionsDto
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
object PermissionsNetworkThunks {
    private val networkScope = CoroutineScope(PlatformDispatcher)
    private val loadPermissions = PermissionsService()

    fun getPermissions(): Thunk<AppState> = { dispatch, getState, extraArg ->
        var response: GatewayResponse<PermissionsDto, GenericError>?
        dispatch(ErrorMessageActions.ClearErrorMessage)
        dispatch(FetchingDataActions.StartNetworkRequest)

        val loggedInUserId = getState().logedInUser.id
        val selectedUserGroupId = getState().selectedUserGroup.id
        val selectedUserId = getState().selectedMember.id

        networkScope.launch(tunkExceptionHandler(dispatch)) {
            response = loadPermissions.loadPermissions(loggedInUserId, selectedUserGroupId, selectedUserId)

            dispatch(FetchingDataActions.EndNetworkRequest)
            if (response != null) {
                if (response!!.isFailure) {
                    dispatch(ErrorMessageActions.SetErrorMessage(response!!.errorResponse?.message))
                } else {
                    dispatch(PermissionsActions.SetPermissions(response!!.response!!))
                }
            }
        }
    }
}
