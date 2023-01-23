package ch.levelup.kaesseli.transaction

fun transactionReducer(state: Set<TransactionDto>, action: Any) =
    when (action) {
        is TransactionActions.SetTransactions -> action.transactions
        else -> state
    }