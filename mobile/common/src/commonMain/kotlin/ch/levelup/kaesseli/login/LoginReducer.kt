package ch.levelup.kaesseli.login

fun loginReducer(state: Login, action: Any) =
    when (action) {
        is LoginActions.ChangeLogin -> getLogin(action.loginInput)
        else -> state
    }


fun getLogin(loginInput: LoginInput): Login {
    if (isEmailValid(loginInput.username)) {
        return Login(username = loginInput.username, isValid = true, isDirty = loginInput.isDirty)
    }
    return Login(username = loginInput.username, isValid = false, isDirty = loginInput.isDirty)
}

const val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";
fun isEmailValid(email: String): Boolean {
    return EMAIL_REGEX.toRegex().matches(email);
}