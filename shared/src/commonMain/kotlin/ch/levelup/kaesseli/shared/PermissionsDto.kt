package ch.levelup.kaesseli.shared

import kotlinx.serialization.Serializable

@Serializable
data class PermissionsDto(
    var executeTranactionsAllowed: Boolean = false,
    val seeOtherGroupMembersAllowed: Boolean = false
)
