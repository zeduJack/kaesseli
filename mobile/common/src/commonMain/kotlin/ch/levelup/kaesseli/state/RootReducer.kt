package ch.levelup.kaesseli.state

import ch.levelup.kaesseli.account.accountReducer
import ch.levelup.kaesseli.errorMessage.errorMessageReducer
import ch.levelup.kaesseli.fetchingData.fetchinDatakReducer
import ch.levelup.kaesseli.login.loginReducer
import ch.levelup.kaesseli.navigation.navigationReducer
import ch.levelup.kaesseli.selectedMember.memberReducer
import ch.levelup.kaesseli.selectedUserGroup.userGroupReducer
import ch.levelup.kaesseli.token.tokenReducer
import ch.levelup.kaesseli.transaction.transactionReducer
import ch.levelup.kaesseli.user.userReducer
import ch.levelup.kaesseli.userRegistration.userRegistrationReducer
import ch.levelup.kaesseli.welcomeMessage.welcomeMessageReducer

fun rootReducer(state: AppState, action: Any) = AppState(
    login = loginReducer(state.login, action),
    navigation = navigationReducer(state.navigation, action),
    welcomeMessage = welcomeMessageReducer(state.welcomeMessage, action),
    fetchingData = fetchinDatakReducer(state.fetchingData, action),
    errorMessage = errorMessageReducer(state.errorMessage, action),
    userRegistration = userRegistrationReducer(state.userRegistration, action),
    logedInUser = userReducer(state.logedInUser, action),
    selectedUserGroup = userGroupReducer(state.selectedUserGroup, action),
    selectedMember = memberReducer(state.selectedMember, action),
    selectedAccount = accountReducer(state.selectedAccount, action),
    currentAccountTransactions = transactionReducer(state.currentAccountTransactions, action),
    token = tokenReducer(state.token, action)
)