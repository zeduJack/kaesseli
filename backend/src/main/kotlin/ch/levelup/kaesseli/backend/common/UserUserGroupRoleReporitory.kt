package ch.levelup.kaesseli.backend.common

import ch.levelup.kaesseli.backend.user.User
import ch.levelup.kaesseli.backend.usergroup.UserGroup
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserUserGroupRoleReporitory : JpaRepository<UserUserGroupRole, Long> {

    fun getUserUserGroupRoleByUserAndUsergroup(user: User, userGroup: UserGroup): List<UserUserGroupRole>
}
