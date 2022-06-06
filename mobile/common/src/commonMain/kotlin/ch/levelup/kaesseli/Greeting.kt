package ch.levelup.kaesseli

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}