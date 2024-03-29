package ch.levelup.kaesseli.backend.scheduler

import ch.levelup.kaesseli.backend.account.Account
import ch.levelup.kaesseli.backend.user.User
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "\"scheduler\"")
data class Scheduler(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val createdAt: LocalDateTime? = LocalDateTime.now(),
    val updatedAt: LocalDateTime? = LocalDateTime.now(),
    val version: Int,

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    val user: User,
    @ManyToOne
    @JoinColumn(name="account_id", nullable=false)
    val account: Account,
    val scheduleTime: String,
    val amount: BigDecimal,
    val message: String
)
