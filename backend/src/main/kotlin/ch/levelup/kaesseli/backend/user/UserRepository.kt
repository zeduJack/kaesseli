package ch.levelup.kaesseli.backend.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun getUserByEmail(email: String): Optional<User>

   // @Query("SELECT * FROM User u  WHERE u.userUserGroups.userGroup = ?1")
   // fun getUsersByUserGroup(userGroup: UserGroup?): Set<User>?
}