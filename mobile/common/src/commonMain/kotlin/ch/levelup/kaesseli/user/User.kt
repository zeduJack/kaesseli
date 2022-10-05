package ch.levelup.kaesseli.user

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Long? = null,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?,
    val version: Int?,

    val firstname: String,
    val lastname: String,
    val username: String?,
    val email: String,
)
