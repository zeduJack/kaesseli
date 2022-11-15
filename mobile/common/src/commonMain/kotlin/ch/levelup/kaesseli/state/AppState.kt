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
    val stringUser: String = "Init",
    val countries: List<String> = listOf("Child 1", "Child 2", "Child 3")
)
