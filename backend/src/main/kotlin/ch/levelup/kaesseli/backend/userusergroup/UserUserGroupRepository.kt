package ch.levelup.kaesseli.backend.userusergroup

import ch.levelup.kaesseli.backend.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserUserGroupRepository : JpaRepository<UserUserGroup, Long> {

    fun getUserUserGroupsByUser(user: User): List<UserUserGroup>?

}