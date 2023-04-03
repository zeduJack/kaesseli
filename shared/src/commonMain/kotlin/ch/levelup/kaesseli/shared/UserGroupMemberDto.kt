package ch.levelup.kaesseli.shared

import kotlinx.serialization.Serializable

@Serializable
data class UserGroupMemberDto(
    val id: Long = -1L,
    val username: String = "",
    val firstname: String = "",
    val lastname: String = "",
){
    var accounts: MutableSet<AccountDto> = mutableSetOf()
    var accountsList: List<AccountDto> = listOf()
    var roles: MutableSet<RoleDto> = mutableSetOf()
    var rolesList: List<RoleDto> = listOf()
    var sumOfAccountsLabel: String = ""
    var accountsLabel: String = ""
    var paymentLabel: String = ""
    var amoutnLabel: String = ""
    var messageLabel: String = ""
}