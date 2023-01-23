package ch.levelup.kaesseli.shared

import kotlinx.serialization.Serializable

@Serializable
data class RoleDto(
    val id: Long,
    val name: String
)
