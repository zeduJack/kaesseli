package ch.levelup.kaesseli

class Greeting {
    fun greeting(): String {
        return "Hello Jack, ${Platform().platform}!"
    }
}