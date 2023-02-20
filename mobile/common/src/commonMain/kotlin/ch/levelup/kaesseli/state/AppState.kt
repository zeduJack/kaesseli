package ch.levelup.kaesseli.state

import ch.levelup.kaesseli.groupView.GroupView
import ch.levelup.kaesseli.login.Login
import ch.levelup.kaesseli.member.Member
import ch.levelup.kaesseli.navigation.Navigation
import ch.levelup.kaesseli.shared.AccountDto
import ch.levelup.kaesseli.shared.LogedInUserDto
import ch.levelup.kaesseli.shared.LogedInUserUserGroupDto
import ch.levelup.kaesseli.transaction.TransactionDto
import ch.levelup.kaesseli.userRegistration.UserRegistration
import ch.levelup.kaesseli.welcomeMessage.WelcomeMessage

data class AppState(
    val login: Login = Login(),
    val navigation: Navigation = Navigation(),
    val welcomeMessage: WelcomeMessage = WelcomeMessage(),
    val fetchingData: Boolean = false,
    val errorMessage: String? = "",
    val userRegistration: UserRegistration? = null,
    val user: LogedInUserDto = LogedInUserDto(),
    val groupView: GroupView = GroupView(),
    val userGroup: LogedInUserUserGroupDto = LogedInUserUserGroupDto(),
    val member: Member = Member(),
    val account: AccountDto = AccountDto(),
    val currentAccountTransactions: Set<TransactionDto> = setOf()
)
