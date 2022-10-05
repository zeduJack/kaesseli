package ch.levelup.kaesseli.user

class UserActions {
    data class SetLoggedInUser(val user: User)
    data class UserCreated(val user: User?)

}
