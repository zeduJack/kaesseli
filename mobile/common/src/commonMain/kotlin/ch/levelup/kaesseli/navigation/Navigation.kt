package ch.levelup.kaesseli.navigation

data class Navigation(val route: String = "", val navigateBack: Boolean = false, val setAsFirstInBackStack: Boolean = false)
