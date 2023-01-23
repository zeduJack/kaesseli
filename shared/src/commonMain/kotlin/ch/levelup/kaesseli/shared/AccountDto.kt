package ch.levelup.kaesseli.shared

import kotlinx.serialization.Serializable

@Serializable
data class AccountDto(
    val id: Long,
    val type: String,
    val saldo: Long,
    val displayName: String
)