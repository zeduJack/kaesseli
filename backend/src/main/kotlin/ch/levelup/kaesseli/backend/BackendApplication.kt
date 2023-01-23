package ch.levelup.kaesseli.backend

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.UserRecord
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@SpringBootApplication
class BackendApplication

fun main(args: Array<String>) {
	initiateFirebase()
	createFirebaseUser("sarah-1")
	runApplication<BackendApplication>(*args)
}

private fun createFirebaseUser(testUid: String){
	// https://firebase.google.com/docs/auth/admin/errors
	val firebaseAuth = FirebaseAuth.getInstance()
	try {
	firebaseAuth.getUser(testUid)
	firebaseAuth.deleteUser(testUid)
	println("Successfully deleted user: " + testUid)
} catch (e: FirebaseAuthException) {
	println("User does not exist: " + testUid)
}
	val request: UserRecord.CreateRequest = UserRecord.CreateRequest()
		.setUid(testUid)
		.setEmail("sarah@kaesseli.ch")
		.setPassword("secretPassword")
		.setDisabled(false)
	val userRecord = firebaseAuth.createUser(request)
	if (testUid != userRecord.uid) {
		println("Error, unexpected user created")
	}
	println("Successfully created new user: " + userRecord.uid)
}

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




@RestController
class MessageResource(val service: MessageService) {
	@PostMapping("token/{token}")
	fun sendMessage(@PathVariable token: String) {
		service.sendMessage(token)
	}
}


@Service
class MessageService() {

	private val LOG = LoggerFactory.getLogger(this::class.java)

	fun sendMessage(token: String) {
		LOG.warn("Token received {}", token)
		Firebase().pushMessage(token)
	}
}

@Service
class FirebaseUserManagement() {

	private val LOG = LoggerFactory.getLogger(this::class.java)

	fun deleteFirebaseUser(uid: String): Boolean {
		val success = Firebase().deleteFirebaseUser(uid)
		LOG.debug("Deleted user with UID: $uid. Status: $success")
		return success
	}

	fun logOutUser(uid: String) {
		Firebase().revokeFirebaseToken(uid)
	}
}