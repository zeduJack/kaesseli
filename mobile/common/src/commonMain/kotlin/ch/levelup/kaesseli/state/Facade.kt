package ch.levelup.kaesseli.state

import ch.levelup.kaesseli.login.LoginActions
import ch.levelup.kaesseli.login.LoginInput
import ch.levelup.kaesseli.user.UserNetworkThunks

object Facade {

    fun logIn() {
        Store.instance.dispatch(UserNetworkThunks.logInUser())
    }

    fun changeLoginInput(username: String) {
        val dirty = !username.isNullOrBlank()
        Store.instance.dispatch(
            LoginActions.ChangeLogin(
                LoginInput(username = username, dirty)
            )
        )
    }
}