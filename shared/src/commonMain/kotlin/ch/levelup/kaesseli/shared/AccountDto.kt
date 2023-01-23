package ch.levelup.kaesseli.shared

import kotlinx.serialization.Serializable

@Serializable
data class AccountDto(
    val type: String,
    val saldo: Long,
    val displayName: String
)