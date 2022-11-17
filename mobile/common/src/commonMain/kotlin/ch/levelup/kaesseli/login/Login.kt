package ch.levelup.kaesseli.login

data class Login(
    val username: String = "",
    val isValid: Boolean = false,
    val isDirty: Boolean = false,
    val loginInvalidMessage: String = "Die E-Mail Adresse ist ungültig",
    val loginTextPlaceholder: String = "Gebe Deinen Benutzernamen ein",
    val loginButtonText: String = "Anmelden",
    val loginFieldLabel: String = "Benutzername"
)

data class LoginInput(
    val username: String = "",
    val isDirty: Boolean = false
)


