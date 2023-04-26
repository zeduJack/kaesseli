package ch.levelup.kaesseli.shared

import kotlinx.serialization.Serializable

@Serializable
data class LogedInUserUserGroupDto(
    val id: Long = INITIAL_ID,
    val name: String = "",
    val isOwner: Boolean = false,
) {
    companion object {
        const val INITIAL_ID: Long = -1L
    }
    //var roles: MutableSet<RoleDto> = mutableSetOf()
    var accounts: MutableSet<AccountDto> = mutableSetOf()
    var accountsList: List<AccountDto> = listOf()
    var members: MutableSet<UserGroupMemberDto> = mutableSetOf()
    var membersList: List<UserGroupMemberDto> = listOf()
    var membersDescription: String = ""
    var membersTitle = ""
}
