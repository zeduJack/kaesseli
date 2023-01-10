package ch.levelup.kaesseli.shared

import kotlinx.serialization.Serializable

@Serializable
data class LogedInUserDto (
    val firstname: String,
    val lastname: String,
    val username: String,
    val email: String,
    val groups: Set<LogedInUserUserGroupDto>
    )