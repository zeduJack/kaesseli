package ch.levelup.kaesseli.shared

import kotlinx.serialization.Serializable

@Serializable
data class LogedInUserUserGroupDto(
    val id: Long,
    val name: String,
    val isOwner: Boolean = false
){
    //var roles: MutableSet<RoleDto> = mutableSetOf();
    var accounts: MutableSet<AccountDto> = mutableSetOf();
    var members: MutableSet<UserGroupMemberDto> = mutableSetOf();
}
