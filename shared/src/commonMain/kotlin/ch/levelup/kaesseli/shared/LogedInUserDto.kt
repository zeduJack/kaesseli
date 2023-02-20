package ch.levelup.kaesseli.shared

import kotlinx.serialization.Serializable

@Serializable
data class LogedInUserDto(
    val id: Long = -1L,
    val firstname: String = "",
    val lastname: String = "",
    val username: String = "",
    var email: String = "",

    ) {
    var groups: MutableSet<LogedInUserUserGroupDto> = mutableSetOf();
}