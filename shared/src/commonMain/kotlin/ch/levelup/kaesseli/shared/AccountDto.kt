package ch.levelup.kaesseli.shared

import kotlinx.serialization.Serializable

@Serializable
data class AccountDto(
    val id: Long = INITIAL_ID,
    val type: String = "",
    val saldo: Long = 0L,
    val displayName: String = ""
    // add transactions
){
    companion object {
        const val INITIAL_ID: Long = -1L
    }
    var transactions: MutableSet<TransactionDto> = mutableSetOf()
    var transactionsList: List<TransactionDto> = listOf()
    var saldoLabel: String = ""
    var accountLabel = ""
    var kontostandLabel = ""
    var creditAccountDescription: String = ""
    var debitAccountDescription: String = ""
    var confirmationButtonLabel: String = "Bestätigen"
    val paymentToggleIsDebit: String = "Geld auszahlen?"
}