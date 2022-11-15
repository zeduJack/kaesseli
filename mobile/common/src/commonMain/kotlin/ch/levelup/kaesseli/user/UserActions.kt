package ch.levelup.kaesseli.user

class UserActions {
    data class LogInUser(val username: String)
    data class LogOutUser(val username: String)
    data class SetUser(val user: User)
    data class UserCreated(val user: User?)

}
