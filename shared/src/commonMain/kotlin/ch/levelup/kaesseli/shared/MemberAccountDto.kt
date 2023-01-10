package ch.levelup.kaesseli.shared

import kotlinx.serialization.Serializable

@Serializable
data class MemberAccountDto(
    val type: String,
    val saldo: Long,
    val displayName: String
)