package ch.levelup.kaesseli.backend.account

import ch.levelup.kaesseli.backend.user.User
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "\"account\"")
data class Account (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val createdAt: LocalDateTime? = LocalDateTime.now(),
    val updatedAt: LocalDateTime? = LocalDateTime.now(),
    val version: Int,

    val type: String,
    val saldo: BigDecimal,
    val displayName: String,

    @OneToOne
    @JoinColumn(name="user_usergroup_id", nullable=false)
    val user: User
)
