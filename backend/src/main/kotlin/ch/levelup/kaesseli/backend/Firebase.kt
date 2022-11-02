package ch.levelup.kaesseli.backend

import com.google.firebase.auth.*
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

    private fun verifyFirebaseIdToken(idToken: String): String? {
        try {
            // idToken comes from the client app see also https://firebase.google.com/docs/auth/admin/verify-id-tokens#java
            val decodedToken: FirebaseToken = FirebaseAuth.getInstance().verifyIdToken(idToken, true)
            return decodedToken.getUid()
        } catch (e: FirebaseAuthException) {
            if (e.getAuthErrorCode() == AuthErrorCode.REVOKED_ID_TOKEN) {
                println("Token has been revoked. Inform the user to re-authenticate or signOut() the user.")
            } else {
                println("Token is invalid.")
            }
            return null
        }
    }

    fun deleteFirebaseUser(uid: String): Boolean {
        return try {
            FirebaseAuth.getInstance().deleteUser(uid);
            true
        } catch (e: Exception) {
            false
        }
    }

    fun revokeFirebaseToken(uid: String){
        FirebaseAuth.getInstance().revokeRefreshTokens(uid)
        val user = FirebaseAuth.getInstance().getUser(uid)
        // Convert to seconds as the auth_time in the token claims is in seconds too.
        val revocationSecond = user.tokensValidAfterTimestamp / 1000
        println("Tokens revoked at: $revocationSecond")
    }
}