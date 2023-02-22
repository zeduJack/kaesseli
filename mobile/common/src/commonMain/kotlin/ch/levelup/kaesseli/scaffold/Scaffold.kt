package ch.levelup.kaesseli.scaffold

data class Scaffold(
    val topBar: TopBar = TopBar(),
    val bottomBar: BottomBar =  BottomBar()
)

data class TopBar(val title: String = "")
data class BottomBar(val homeTitle: String = "Home", val profileTitle: String = "Profil")