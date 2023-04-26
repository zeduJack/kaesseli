package ch.levelup.kaesseli.shared

import kotlinx.serialization.Serializable

@Serializable
data class LogedInUserDto(
    val id: Long = INITIAL_ID,
    val firstname: String = "",
    val lastname: String = "",
    val username: String = "",
    var email: String = "",

    ) {
    companion object {
        const val INITIAL_ID: Long = -1L
    }
    var groups: MutableSet<LogedInUserUserGroupDto> = mutableSetOf()
    var groupsList: List<LogedInUserUserGroupDto> = listOf()
    var groupLabel = ""
}