package ch.levelup.kaesseli.backend.mobile

import ch.levelup.kaesseli.backend.common.Role
import ch.levelup.kaesseli.backend.common.UserGroupDto
import ch.levelup.kaesseli.backend.user.User
import ch.levelup.kaesseli.backend.user.UserRepository
import ch.levelup.kaesseli.backend.user.UserService
import ch.levelup.kaesseli.backend.usergroup.UserGroup
import ch.levelup.kaesseli.backend.usergroup.UserGroupRepository
import ch.levelup.kaesseli.backend.usergroup.UserGroupService
import ch.levelup.kaesseli.shared.LogedInUserDto
import ch.levelup.kaesseli.shared.LogedInUserUserGroupDto
import ch.levelup.kaesseli.shared.RoleDto
import ch.levelup.kaesseli.shared.UserGroupMemberDto
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class MobileService(
    private val userRepository: UserRepository,

    private val userGroupRepository: UserGroupRepository
    ){

    fun getLogedInUserByEmail(email: String): Optional<LogedInUserDto> {
        val userOptional = userRepository.getUserByEmail(email);

        if(userOptional.isPresent){
            val user = userOptional.get();
            var logedInUser = mapUserDto(user);

            user.userGroups.forEach { group ->
                val uGroup = mapUserGroupDto(group);
                uGroup.members.addAll(group.users.map {user ->
                    mapUserGroupMemberDto(user) } )
                logedInUser.groups.add(uGroup)
            }

            if(logedInUser != null){
                return Optional.of(logedInUser);
            }
        }
        return Optional.empty()
    }

    private fun mapUserDto(user: User) = LogedInUserDto(
        id = user.id ?: 0L,
        firstname = user.firstname,
        lastname = user.lastname,
        email = user.email,
        username = user.username ?: ""
    )

    private fun mapUserGroupDto(userGroup: UserGroup) = LogedInUserUserGroupDto(
        id = userGroup.id ?: 0L,
        name = userGroup.name
    )

    private fun mapUserGroupMemberDto(user: User) = UserGroupMemberDto(
        id = user.id ?: 0L,
        username = user.username ?: "",
        firstname = user.firstname,
        lastname = user.lastname
    )

    private fun mapRole(role: Role) = RoleDto(
        id = role.id ?: 0L,
        name = role.name
    )
}
̨