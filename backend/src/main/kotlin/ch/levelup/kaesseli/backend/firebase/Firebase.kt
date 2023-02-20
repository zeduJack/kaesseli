package ch.levelup.kaesseli.backend.firebase

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.*
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class Firebase() {

    private val logger = LoggerFactory.getLogger(this::class.java)

    private fun initiateFirebase() {
        val encodedToken = System.getenv("FIREBASE_TOKEN")
        val decodedToken = String(Base64.getDecoder().decode(encodedToken))
        val credential = GoogleCredentials.fromStream(decodedToken.byteInputStream())

        val options = FirebaseOptions.builder()
                .setCredentials(credential)
                .setProjectId("kaesseli-18cf8")
                .build()

        FirebaseApp.initializeApp(options)
    }

    fun pushMessage(token: String, title: String, body: String): String? {
        val notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build()
        val message = Message.builder()
                .setNotification(notification)
                .setToken(token)
                .build()
        return FirebaseMessaging.getInstance().send(message)
    }

    fun createFirebaseUser(firebaseUid: String, email: String, pwd: String) {
        // https://firebase.google.com/docs/auth/admin/errors
        val firebaseAuth = FirebaseAuth.getInstance()
        try {
            firebaseAuth.getUser(firebaseUid)
            firebaseAuth.deleteUser(firebaseUid)
            println("Successfully deleted user: $firebaseUid")
        } catch (e: FirebaseAuthException) {
            println("User does not exist: $firebaseUid")
        }
        val request: UserRecord.CreateRequest = UserRecord.CreateRequest()
                .setUid(firebaseUid)
                .setEmail(email)
                .setPassword(pwd)
                .setDisabled(false)
        val userRecord = firebaseAuth.createUser(request)
        if (firebaseUid != userRecord.uid) {
            println("Error, unexpected user created")
        }
        println("Successfully created new user: " + userRecord.uid)
    }

    fun deleteFirebaseUser(uid: String): Boolean {
        val success = Firebase().deleteFirebaseUser(uid)
        logger.debug("Deleted user with UID: $uid. Status: $success")
        return success
    }

    fun logOutUser(uid: String) {
        Firebase().revokeFirebaseAccessToken(uid)
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

    private fun revokeFirebaseAccessToken(uid: String) {
        FirebaseAuth.getInstance().revokeRefreshTokens(uid)
        val user = FirebaseAuth.getInstance().getUser(uid)
        // Convert to seconds as the auth_time in the token claims is in seconds too.
        val revocationSecond = user.tokensValidAfterTimestamp / 1000
        println("Tokens revoked at: $revocationSecond")
    }

    init {
        initiateFirebase()
    }
}