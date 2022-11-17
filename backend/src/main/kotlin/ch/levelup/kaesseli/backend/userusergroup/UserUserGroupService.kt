package ch.levelup.kaesseli.backend.userusergroup

import ch.levelup.kaesseli.backend.user.User
import org.springframework.stereotype.Service

@Service
class UserUserGroupService(private val userUserGroupRepository: UserUserGroupRepository) {

    fun getUserUserGroupByUserId(user: User): List<UserUserGroup>? =
        userUserGroupRepository.getUserUserGroupsByUser(user)

}