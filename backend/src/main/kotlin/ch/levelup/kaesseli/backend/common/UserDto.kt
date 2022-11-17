package ch.levelup.kaesseli.backend.common

import java.math.BigDecimal

data class UserDto(
    val firstname: String,
    val lastname: String,
    val username: String?,
    val email: String,
    val userGroups: Set<UserGroupDto>?
)

data class UserGroupDto(
    val name: String,
    val members: Set<MemberDto>?
)

data class MemberDto(
    val firstname: String,
    val lastname: String,
    val accounts: Set<AccountDto>?
)

data class AccountDto(
    val id: Long?,
    val type: String,
    val saldo: BigDecimal,
    val displayName: String
)