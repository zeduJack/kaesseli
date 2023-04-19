package ch.levelup.kaesseli.shared

import kotlinx.serialization.Serializable

@Serializable
data class TransactionDto(
    val id: Long,
    val createdAt: String,
    val amount: String,
    val username: String,
    val userFirstname: String,
    val userLastname: String,
    val debit: Boolean,
    val message: String,
    val status: String,
    val resultingSaldo: String,
    val chartLabel: String
) {
}