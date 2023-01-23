package ch.levelup.kaesseli.backend

import ch.levelup.kaesseli.backend.firebase.Firebase
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@SpringBootApplication
class BackendApplication

fun main(args: Array<String>) {
	Firebase().initiateFirebase()
	runApplication<BackendApplication>(*args)
}

@RestController
class MessageResource(val service: Firebase) {
	@PostMapping("token/{token}")
	fun sendMessage(@PathVariable token: String, title: String, body: String) {
		service.pushMessage(token, title, body)
	}
}
