package ch.levelup.kaesseli.shared

import kotlinx.serialization.Serializable

@Serializable
data class AccountDto(
    val id: Long = -1L,
    val type: String = "",
    val saldo: Long = 0L,
    val displayName: String = ""
    // add transactions
){
    var transactions: MutableSet<TransactionDto> = mutableSetOf()
    var saldoLabel: String = ""
    var accountLabel = ""
    var kontostandLabel = ""
    var creditAccountDescription: String = ""
    var debitAccountDescription: String = ""
    var confirmationButtonLabel: String = "Bestätigen"
    val paymentToggleIsDebit: String = "Geld auszahlen?"
}