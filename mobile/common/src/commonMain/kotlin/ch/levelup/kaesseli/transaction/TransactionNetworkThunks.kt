package ch.levelup.kaesseli.transaction

import ch.levelup.kaesseli.GatewayResponse
import ch.levelup.kaesseli.GenericError
import ch.levelup.kaesseli.PlatformDispatcher
import ch.levelup.kaesseli.errorMessage.ErrorMessageActions
import ch.levelup.kaesseli.fetchingData.FetchingDataActions
import ch.levelup.kaesseli.shared.NewTransactionDto
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
object TransactionNetworkThunks {
    private val transactionRepository = TransactionRepository()
    private val networkScope = CoroutineScope(PlatformDispatcher)

    fun getTransactions(): Thunk<AppState> = { dispatch, getState, extraArg ->
        var response: GatewayResponse<Set<TransactionDto>, GenericError>?
        dispatch(ErrorMessageActions.ClearErrorMessage)
        dispatch(FetchingDataActions.StartNetworkRequest)

        networkScope.launch(tunkExceptionHandler(dispatch)) {
            response = transactionRepository.logInUser(getState().selectedAccount.id)

            dispatch(FetchingDataActions.EndNetworkRequest)
            if (response != null) {
                if (response!!.isFailure) {
                    dispatch(ErrorMessageActions.SetErrorMessage(response!!.errorResponse?.message))
                } else {
                    if (response?.response != null) {
                        dispatch(TransactionActions.SetTransactions(response!!.response!!))
                    }
                }
            }
        }
    }

    fun addTransaction(amount: Long, message: String): Thunk<AppState> = { dispatch, getState, extraArg ->
        var response: GatewayResponse<Unit, GenericError>?
        dispatch(ErrorMessageActions.ClearErrorMessage)
        dispatch(FetchingDataActions.StartNetworkRequest)

        val newTransactionDto = NewTransactionDto(
            amount = amount,
            debit = false,
            message = message,
            logedInUserEmail = getState().logedInUser.email,
            accountId = getState().selectedAccount.id
        )

        networkScope.launch(tunkExceptionHandler(dispatch)) {
            response = transactionRepository.addTransaction(newTransactionDto)

            dispatch(FetchingDataActions.EndNetworkRequest)
            if (response != null) {
                if (response!!.isFailure) {
                    dispatch(ErrorMessageActions.SetErrorMessage(response!!.errorResponse?.message))
                } else {
                    // todo: load all user data and navigate back
                }
            }
        }
    }
}
