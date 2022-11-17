package ch.levelup.kaesseli.backend.user

import ch.levelup.kaesseli.backend.usergroup.UserGroup
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun getUserByEmail(email: String): Optional<User>

    fun getUsersByUserGroups(userGroup: UserGroup?): Set<User>?
}