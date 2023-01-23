package ch.levelup.kaesseli.shared

import kotlinx.serialization.Serializable

@Serializable
data class LogedInUserDto (
    val firstname: String,
    val lastname: String,
    val username: String,
    var email: String,

    ){
    var groups: Set<LogedInUserUserGroupDto> = setOf();
}