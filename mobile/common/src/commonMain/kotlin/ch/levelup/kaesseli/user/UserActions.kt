package ch.levelup.kaesseli.user

import ch.levelup.kaesseli.shared.LogedInUserDto

class UserActions {
    data class SetUser(val user: LogedInUserDto)
}
