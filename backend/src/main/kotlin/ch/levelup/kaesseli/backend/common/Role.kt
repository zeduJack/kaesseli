package ch.levelup.kaesseli.backend.common

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "\"role\"")
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val createdAt: LocalDateTime? = LocalDateTime.now(),
    val updatedAt: LocalDateTime? = LocalDateTime.now(),
    val version: Int,

    val name: String
)
