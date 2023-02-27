package ch.levelup.kaesseli.backend.transaction

import ch.levelup.kaesseli.backend.account.Account
import ch.levelup.kaesseli.backend.user.User
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
    var updatedAt: LocalDateTime? = LocalDateTime.now(),
    val version: Int,

    val amount: BigDecimal,
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    val account: Account,
    val debit: Boolean,
    var status: String,
    var message: String
)
