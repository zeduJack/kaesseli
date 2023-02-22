package ch.levelup.kaesseli.shared

import kotlinx.serialization.Serializable

@Serializable
data class NewTransactionDto(
    val amount: Long,
    val debit: Boolean,
    val message: String,
    val logedInUserEmail: String,
    val accountId: Long,
)