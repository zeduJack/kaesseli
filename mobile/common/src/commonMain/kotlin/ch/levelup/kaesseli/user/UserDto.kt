package ch.levelup.kaesseli.user

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val firstname: String = "",
    val lastname: String = "",
    val username: String = "",
    val email: String = "",
    val userGroups: List<UserGroupDto> = listOf(UserGroupDto())
)

@Serializable
data class UserGroupDto(
    val name: String = "",
    val members: List<MemberDto> = listOf(MemberDto())
)

@Serializable
data class MemberDto(
    val firstname: String = "",
    val lastname: String = "",
    val accounts: List<AccountDto> = listOf(AccountDto())
)

@Serializable
data class AccountDto(
    val id: Double = -1.0,
    val type: String = "",
    val saldo: Double = -1.0,
    val displayName: String = ""
)