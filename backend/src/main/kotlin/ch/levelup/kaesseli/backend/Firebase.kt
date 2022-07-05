package ch.levelup.kaesseli.backend

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification


class Firebase {

    fun pushMessage(token: String): String? {
        val notification = Notification.builder()
            .setTitle("Welcome")
            .setBody("Hi there, welcome to Kässeli")
            .build()
        val message = Message.builder()
            .setNotification(notification)
            .setToken(token)
            .build()
        return FirebaseMessaging.getInstance().send(message)
    }
}