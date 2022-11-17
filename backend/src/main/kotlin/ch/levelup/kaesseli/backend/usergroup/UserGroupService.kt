package ch.levelup.kaesseli.backend.usergroup

import ch.levelup.kaesseli.backend.user.UserService
import ch.levelup.kaesseli.backend.userusergroup.UserUserGroupService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class UserGroupService(private val userGroupRepository: UserGroupRepository,
                       private val userUserGroupService: UserUserGroupService,
                       private val userService: UserService) {


    fun getUserGroups(): List<UserGroup> = userGroupRepository.findAll()

    fun getUserGroupById(userGroupId: Long): ResponseEntity<UserGroup> =
        userGroupRepository.findById(userGroupId).map { userGroup ->
            ResponseEntity.ok(userGroup)
        }.orElse(ResponseEntity.notFound().build())

    fun getUserGroupsByUserId(id: Long): MutableList<UserGroup>? {
        val user = userService.getUserByIdNoFun(id).get()
        return userUserGroupService.getUserUserGroupByUserId(user)
            ?.stream()
            ?.map { userUserGroup -> userUserGroup.usergroup }
            ?.collect(Collectors.toList())
    }


    fun addUserGroup(groupe: UserGroup): ResponseEntity<UserGroup> = ResponseEntity.ok(userGroupRepository.save(groupe))

    fun putUserGroup(userGroupId: Long, newUserGroup: UserGroup): ResponseEntity<UserGroup> =
        userGroupRepository.findById(userGroupId).map { currentUserGroup ->
            val updateUserGroup: UserGroup = currentUserGroup.copy(
                    name = newUserGroup.name
                )
            ResponseEntity.ok().body(userGroupRepository.save(updateUserGroup))
        }.orElse(ResponseEntity.notFound().build())

    fun deleteUserGroup(userGroupId: Long): ResponseEntity<Void> =
        userGroupRepository.findById(userGroupId).map { userGroup ->
        userGroupRepository.delete(userGroup)
        ResponseEntity<Void>(HttpStatus.ACCEPTED)
    }.orElse(ResponseEntity.notFound().build())

}