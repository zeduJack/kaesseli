package ch.levelup.kaesseli.shared

import kotlinx.serialization.Serializable

@Serializable
data class UserGroupMemberDto(
    val name: String,

){
    var roles: Set<RoleDto> = setOf();
}