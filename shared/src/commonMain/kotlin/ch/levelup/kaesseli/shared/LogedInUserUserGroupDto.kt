package ch.levelup.kaesseli.shared

import kotlinx.serialization.Serializable

@Serializable
data class LogedInUserUserGroupDto(
    val name: String,
    val members: Set<UserGroupMemberDto>
)
