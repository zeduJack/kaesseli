package ch.levelup.kaesseli.transaction

class TransactionActions {
    data class SetTransactions(val transactions: Set<TransactionDto>)
}
