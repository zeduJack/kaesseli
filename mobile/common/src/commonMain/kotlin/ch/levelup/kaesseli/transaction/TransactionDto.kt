package ch.levelup.kaesseli.transaction

import kotlinx.serialization.Serializable

@Serializable
data class TransactionDto(
    val createdAt: String,
    val updatedAt: String,
    val debit: Boolean,
    val status: String,
    val message: String,
    val amount: Double
)