package ch.levelup.kaesseli.backend.transaction

import ch.levelup.kaesseli.backend.common.TransactionStatus
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "\"transaction\"")
data class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val createdAt: LocalDateTime? = LocalDateTime.now(),
    val updatedAt: LocalDateTime? = LocalDateTime.now(),
    val version: Int,

    val amount: BigDecimal,
    //val user: User,
    //val account: Account,
    val debit: Boolean,
    val status: TransactionStatus,
    val message: String


    )
