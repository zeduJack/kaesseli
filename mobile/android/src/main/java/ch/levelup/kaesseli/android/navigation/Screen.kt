package ch.levelup.kaesseli.android.navigation

sealed class Screen(val route: String) {
    object MainScreen: Screen("main")
    object BalanceScreen: Screen("balance")
    object OverviewScreen: Screen("overview")
    object RegisterScreen: Screen("register")
    object CodeSubmissionScreen: Screen("code_submission")
    object RecompositionTestScreen: Screen("recomposition_test")
    object NetworkTestScreen: Screen("network_test")
}
