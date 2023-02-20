package ch.levelup.kaesseli

sealed class ScreenNavigation(val route: String) {
    object LoginScreen: ScreenNavigation("login")
    object MainScreen: ScreenNavigation("main")
    object BalanceScreen: ScreenNavigation("balance")
    object OverviewScreen: ScreenNavigation("overview")
    object StartScreen: ScreenNavigation("start")
    object GroupMembersScreen: ScreenNavigation("group_members")
    object RegisterScreen: ScreenNavigation("register")
    object CodeSubmissionScreen: ScreenNavigation("code_submission")
    object MemberScreen: ScreenNavigation("member")
    object PaymentScreen: ScreenNavigation("payment")
}
