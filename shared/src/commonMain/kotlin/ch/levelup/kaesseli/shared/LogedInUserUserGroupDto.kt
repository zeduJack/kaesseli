package ch.levelup.kaesseli.shared

import kotlinx.serialization.Serializable

@Serializable
data class LogedInUserUserGroupDto(
    val name: String
){
    var roles: Set<RoleDto> = setOf();
    var accounts: Set<AccountDto> = setOf();
    var members: Set<UserGroupMemberDto> = setOf();
}
