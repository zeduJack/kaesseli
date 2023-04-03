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
    var transactionsList: List<TransactionDto> = listOf()
    var saldoLabel: String = ""
    var accountLabel = ""
    var kontostandLabel = ""
    var paymentAccountDescription: String = ""
}