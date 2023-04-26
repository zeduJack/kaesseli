package ch.levelup.kaesseli.shared

import kotlinx.serialization.Serializable

@Serializable
data class UserGroupMemberDto(
    val id: Long = INITIAL_ID,
    val username: String = "",
    val firstname: String = "",
    val lastname: String = "",
){
    companion object {
        const val INITIAL_ID: Long = -1L
    }
    var accounts: MutableSet<AccountDto> = mutableSetOf()
    var accountsList: List<AccountDto> = listOf()
    var roles: MutableSet<RoleDto> = mutableSetOf()
    var rolesList: List<RoleDto> = listOf()
    var sumOfAccountsLabel: String = ""
    var accountsLabel: String = ""
    var debitLabel: String = ""
    var creditLabel: String = ""
    var amountLabel: String = ""
    var messageLabel: String = ""
}