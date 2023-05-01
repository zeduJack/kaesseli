package ch.levelup.kaesseli.state

import ch.levelup.kaesseli.login.Login
import ch.levelup.kaesseli.navigation.Navigation
import ch.levelup.kaesseli.scaffold.Scaffold
import ch.levelup.kaesseli.shared.*
import ch.levelup.kaesseli.transaction.TransactionDto
import ch.levelup.kaesseli.userRegistration.UserRegistration
import ch.levelup.kaesseli.welcomeMessage.WelcomeMessage

data class AppState(
    val scaffold: Scaffold = Scaffold(),
    val login: Login = Login(),
    val navigation: Navigation = Navigation(),
    val welcomeMessage: WelcomeMessage = WelcomeMessage(),
    val fetchingData: Boolean = false,
    val errorMessage: String? = "",
    val userRegistration: UserRegistration? = null,
    val logedInUser: LogedInUserDto = LogedInUserDto(),
    val selectedUserGroup: LogedInUserUserGroupDto = LogedInUserUserGroupDto(),
    val selectedMember: UserGroupMemberDto = UserGroupMemberDto(),
    val selectedAccount: AccountDto = AccountDto(),
    val currentAccountTransactions: Set<TransactionDto> = setOf(),
    val token: String = "",
    val permissions: PermissionsDto = PermissionsDto()
)
