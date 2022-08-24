package ch.levelup.kaesseli.backend.user

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun getUsers(): List<User> = userRepository.findAll()

    fun getUserById(userId: Long): ResponseEntity<User> =
        userRepository.findById(userId).map { user ->
            ResponseEntity.ok(user)
        }.orElse(ResponseEntity.notFound().build())

    fun addUser(user: User): ResponseEntity<User> =
        ResponseEntity.ok(userRepository.save(user))

    fun putUser(userId: Long, newUser: User): ResponseEntity<User> =
        userRepository.findById(userId).map { currentUser ->
            val updateUser: User = currentUser.copy(
                firstname = newUser.firstname,
                lastname = newUser.lastname,
                email = newUser.email,
                username = newUser.username
            )
            ResponseEntity.ok().body(userRepository.save(updateUser))
        }.orElse(ResponseEntity.notFound().build())

    fun deleteUser(userId: Long): ResponseEntity<Void> =
        userRepository.findById(userId).map { user ->
            userRepository.delete(user)
            ResponseEntity<Void>(HttpStatus.ACCEPTED)
        }.orElse(ResponseEntity.notFound().build())
}