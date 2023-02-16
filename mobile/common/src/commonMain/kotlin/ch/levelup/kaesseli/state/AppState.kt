package ch.levelup.kaesseli.state

import ch.levelup.kaesseli.groupView.GroupView
import ch.levelup.kaesseli.login.Login
import ch.levelup.kaesseli.member.Member
import ch.levelup.kaesseli.navigation.Navigation
import ch.levelup.kaesseli.transaction.TransactionDto
import ch.levelup.kaesseli.user.AccountDto
import ch.levelup.kaesseli.user.UserDto
import ch.levelup.kaesseli.user.UserGroupDto
import ch.levelup.kaesseli.userRegistration.UserRegistration
import ch.levelup.kaesseli.welcomeMessage.WelcomeMessage

data class AppState(
    val login: Login = Login(),
    val navigation: Navigation = Navigation(),
    val welcomeMessage: WelcomeMessage = WelcomeMessage(),
    val fetchingData: Boolean = false,
    val errorMessage: String? = "",
    val userRegistration: UserRegistration? = null,
    val user: UserDto = UserDto(),
    val groupView: GroupView = GroupView(),
    val userGroup: UserGroupDto = UserGroupDto(),
    val member: Member = Member(),
    val account: AccountDto = AccountDto(),
    val currentAccountTransactions: Set<TransactionDto> = setOf()
)
