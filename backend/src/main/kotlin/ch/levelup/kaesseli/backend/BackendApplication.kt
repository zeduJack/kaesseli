package ch.levelup.kaesseli.backend

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController


@SpringBootApplication
class BackendApplication

fun main(args: Array<String>) {
	runApplication<BackendApplication>(*args)
	//initiateFirebase()
}

private fun initiateFirebase() {
	val options = FirebaseOptions.builder()
		.setCredentials(GoogleCredentials.getApplicationDefault())
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
