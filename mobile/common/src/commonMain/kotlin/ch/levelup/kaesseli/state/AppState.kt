package ch.levelup.kaesseli.state

import ch.levelup.kaesseli.user.User
import ch.levelup.kaesseli.userRegistration.UserRegistration

data class AppState(
    val navigation: String = "",
    val welcomeMessage: String = "Bitte loggen sie sich ein",
    val fetchingData: Boolean = false,
    val errorMessage: String? = "",
    val userRegistration: UserRegistration? = null,
    val user: User = User(),
    val userGroup: String = "",
    val stringUser: String = "Init",
    val userGroups: List<String> = listOf("Familie 1", "Familie 2", "Nachbarschaft 3"),
    val groupMembers: List<String> = listOf("Melanie", "Choesang", "Zeljko", "Sarah")
)
