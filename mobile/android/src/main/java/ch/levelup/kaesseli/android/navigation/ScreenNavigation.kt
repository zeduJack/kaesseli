package ch.levelup.kaesseli.android.navigation

sealed class ScreenNavigation(val route: String) {
    object LoginScreen: ScreenNavigation("login")
    object MainScreen: ScreenNavigation("main")
    object BalanceScreen: ScreenNavigation("balance")
    object OverviewScreen: ScreenNavigation("overview")
    object UserGroupOverviewScreen: ScreenNavigation("user_group_overview")
    object GroupMembersScreen: ScreenNavigation("group_mebers")
    object RegisterScreen: ScreenNavigation("register")
    object CodeSubmissionScreen: ScreenNavigation("code_submission")
    object RecompositionTestScreen: ScreenNavigation("recomposition_test")
    object NetworkTestScreen: ScreenNavigation("network_test")
}
