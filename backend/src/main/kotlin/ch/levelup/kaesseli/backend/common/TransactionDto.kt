package ch.levelup.kaesseli.backend.common

import java.math.BigDecimal
import java.time.LocalDateTime

data class TransactionDto(
    val createdAt: LocalDateTime? = LocalDateTime.now(),
    val updatedAt: LocalDateTime? = LocalDateTime.now(),
    val debit: Boolean,
    val status: String,
    val message: String,
    val amount: BigDecimal
)
