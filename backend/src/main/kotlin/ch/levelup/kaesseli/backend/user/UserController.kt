package ch.levelup.kaesseli.backend.user


import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController(private val userService: UserService) {

    @GetMapping
    fun getUsers(): List<User> = userService.getUsers()

    @GetMapping("/{id}")
    fun getUserById(@PathVariable(value = "id") userId: Long): ResponseEntity<User> =
        userService.getUserById(userId)

    @PostMapping
    fun addUser(@RequestBody user: User): ResponseEntity<User> =
        userService.addUser(user)

    @PutMapping("/{id}")
    fun updateUserById(
        @PathVariable(value = "id") userId: Long,
        @RequestBody newUser: User
    ): ResponseEntity<User> =
        userService.putUser(userId, newUser)

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable(value = "id") userId: Long): ResponseEntity<Void> =
        userService.deleteUser(userId)
}