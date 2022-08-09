package ch.levelup.kaesseli.backend.usergroup

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class UserGroupService(private val userGroupRepository: UserGroupRepository) {

    fun getUserGroups(): List<UserGroup> = userGroupRepository.findAll()

    fun getUserGroupById(userGroupId: Long): ResponseEntity<UserGroup> =
        userGroupRepository.findById(userGroupId).map { userGroup ->
            ResponseEntity.ok(userGroup)
        }.orElse(ResponseEntity.notFound().build())

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