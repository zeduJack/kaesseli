package ch.levelup.kaesseli.shared

import kotlinx.serialization.Serializable

@Serializable
data class UserGroupMemberDto(
    val name: String,
    val roles: Set<RoleDto>,
    val accounts: Set<MemberAccountDto>
)