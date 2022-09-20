package ch.levelup.kaesseli.android.navigation

sealed class Screen(val route: String) {
    object MainScreen: Screen("main")
    object BalanceScreen: Screen("balance")
    object OverviewScreen: Screen("overview")
    object RegisterScreen: Screen("register")
    object CodeSubmissionScreen: Screen("code_submission")
}
