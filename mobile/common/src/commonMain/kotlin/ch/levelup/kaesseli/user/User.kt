package ch.levelup.kaesseli.user

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Long = -1,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
    val version: Int? = null,

    val firstname: String = "",
    val lastname: String = "",
    val username: String = "DEFAULT",
    val email: String = "",
){
    companion object {
        const val defaultUserId = -1L
    }
}
