package ch.levelup.kaesseli.backend.user


import ch.levelup.kaesseli.backend.common.UserDto
import ch.levelup.kaesseli.backend.usergroup.UserGroupService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService,
private val userGroupService: UserGroupService) {

    @GetMapping
    fun getUsers(): List<User> = userService.getUsers()

    @GetMapping("/{id}")
    fun getUserById(@PathVariable(value = "id") userId: Long): ResponseEntity<User> =
        userService.getUserById(userId)

    @GetMapping("email/{email}")
    fun getUserByEmail(@PathVariable(value = "email") email: String): ResponseEntity<User>? =
        userService.getUserByEmail(email)

    @GetMapping("data/{email}")
    fun getUserDataByEmail(@PathVariable(value = "email") email: String): ResponseEntity<UserDto>? =
        userService.getUserDtoByEmail(email)

    @GetMapping("userGroup/{userGroupId}")
    fun getUsersByUserGroup(@PathVariable(value = "userGroupId") userGroupId: Long) : Set<User>? {
        val userGroup = userGroupService.getUsersByUserGroupId(userGroupId).get()
        return userService.getUsers(userGroup)
    }

    @PostMapping
    fun addUser(@RequestBody user: User): ResponseEntity<User> = userService.addUser(user)

    @PutMapping("/{id}")
    fun updateUserById(
        @PathVariable(value = "id") userId: Long,
        @RequestBody newUser: User
    ): ResponseEntity<User> = userService.putUser(userId, newUser)

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable(value = "id") userId: Long): ResponseEntity<Void> = userService.deleteUser(userId)
}