package ch.levelup.kaesseli.scaffold

import ch.levelup.kaesseli.ScreenNavigation

//fun scaffoldReducer(state: Scaffold, action: Any) =
//    when (action) {
//        is UserActions.SetUser -> Scaffold(topBar = TopBar(action.user.groupLabel))
//        is UserGroupActions.SetSelectedGroup -> Scaffold(topBar = TopBar(action.group.membersTitle))
//        is SelectedMemberActions.SetSelectedMember -> Scaffold(topBar = TopBar(action.userGroupMemberDto.accountsLabel))
//        is AccountActions.SetAccount -> Scaffold(topBar = TopBar(action.account.accountLabel))
//        else -> state
//    }

fun scaffoldReducer(state: Scaffold, action: Any) =
    when (action) {
        is ScafffoldActions.SetTobBarTitle -> getScaffold(action)
        else -> state
    }

fun getScaffold(action: ScafffoldActions.SetTobBarTitle): Scaffold {
    if (ScreenNavigation.GroupsScreen.route == action.appState.navigation.route) {
        return Scaffold(topBar = TopBar(action.appState.logedInUser.groupLabel))
    }

    if (ScreenNavigation.GroupMembersScreen.route == action.appState.navigation.route) {
        return Scaffold(topBar = TopBar(action.appState.selectedUserGroup.membersTitle))
    }

    if (ScreenNavigation.AccountScreeen.route == action.appState.navigation.route) {
        return Scaffold(topBar = TopBar(action.appState.selectedAccount.accountLabel))
    }

    if (ScreenNavigation.MemberScreen.route == action.appState.navigation.route) {
        return Scaffold(topBar = TopBar(action.appState.selectedMember.accountsLabel))
    }

    if (ScreenNavigation.MemberScreen.route == action.appState.navigation.route) {
        return Scaffold(topBar = TopBar(action.appState.selectedMember.accountsLabel))
    }

    if (ScreenNavigation.CreditScreen.route == action.appState.navigation.route) {
        return Scaffold(topBar = TopBar(action.appState.selectedMember.creditLabel))
    }

    if (ScreenNavigation.DebitScreen.route == action.appState.navigation.route) {
        return Scaffold(topBar = TopBar(action.appState.selectedMember.debitLabel))
    }

    return Scaffold(TopBar())
}
