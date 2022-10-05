package ch.levelup.kaesseli.state

import ch.levelup.kaesseli.userRegistration.UserRegistration
import ch.levelup.kaesseli.user.User

data class AppState(
    val fetchingData: Boolean = false,
    val errorMessage: String? = "",
    val userRegistration: UserRegistration? = null,
    val user: User? = null,
    val stringUser: String = "Init",
    val countries: List<String> = listOf("Child 1", "Child 2", "Child 3")
)
