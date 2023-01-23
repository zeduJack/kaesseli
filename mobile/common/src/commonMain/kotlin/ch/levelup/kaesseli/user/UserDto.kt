package ch.levelup.kaesseli.user

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val firstname: String = "",
    val lastname: String = "",
    val username: String = "",
    val email: String = "",
    val userGroups: Set<UserGroupDto> = setOf(UserGroupDto())
)

@Serializable
data class UserGroupDto(
    val name: String = "",
    val members: Set<MemberDto> = setOf(MemberDto())
)

@Serializable
data class MemberDto(
    val firstname: String = "",
    val lastname: String = "",
    val accounts: Set<AccountDto> = setOf(AccountDto())
)

@Serializable
data class AccountDto(
    val id: Int = -1,
    val type: String = "",
    val saldo: Double = -1.0,
    val displayName: String = ""
)