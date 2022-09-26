package ch.levelup.kaesseli.backend.transaction

import ch.levelup.kaesseli.backend.account.Account
import ch.levelup.kaesseli.backend.common.TransactionStatus
import ch.levelup.kaesseli.backend.user.User
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

data class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val createdAt: LocalDateTime? = LocalDateTime.now(),
    val updatedAt: LocalDateTime? = LocalDateTime.now(),
    val version: Int,

    val amount: BigDecimal,
    val user: User,
    val account: Account,
    val debit: Boolean,
    val status: TransactionStatus,
    val message: String


    )
