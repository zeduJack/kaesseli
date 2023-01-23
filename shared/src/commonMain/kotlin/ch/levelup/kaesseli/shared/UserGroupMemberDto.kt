package ch.levelup.kaesseli.shared

import kotlinx.serialization.Serializable

@Serializable
data class UserGroupMemberDto(
    val id: Long,
    val username: String,
    val firstname: String,
    val lastname: String,
){
    var accounts: MutableSet<AccountDto> = mutableSetOf();
    var roles: MutableSet<RoleDto> = mutableSetOf();
}