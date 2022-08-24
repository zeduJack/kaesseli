package ch.levelup.kaesseli.backend.usergroup

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/userGroup")
class UserGroupController(private val userGroupService: UserGroupService) {
    @GetMapping
    fun getUserGroups(): List<UserGroup> =
        userGroupService.getUserGroups()

    @GetMapping("/{id}")
    fun getUserGroupById(@PathVariable(value = "id") userGroupId: Long): ResponseEntity<UserGroup> =
        userGroupService.getUserGroupById(userGroupId)

    @PostMapping
    fun addUserGroup(@RequestBody userGroup: UserGroup): ResponseEntity<UserGroup> =
        userGroupService.addUserGroup(userGroup)

    @PutMapping("/{id}")
    fun updateUserGroupById(
        @PathVariable(value = "id") userGroupId: Long,
        @RequestBody newUserGroup: UserGroup
    ): ResponseEntity<UserGroup> =
        userGroupService.putUserGroup(userGroupId, newUserGroup)

    @DeleteMapping("/{id}")
    fun deleteUserGroup(@PathVariable(value = "id") userGroupId: Long): ResponseEntity<Void> =
        userGroupService.deleteUserGroup(userGroupId)
}