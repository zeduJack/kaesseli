package ch.levelup.kaesseli.backend.account

import ch.levelup.kaesseli.backend.common.UserUserGroup
import ch.levelup.kaesseli.backend.user.User
import ch.levelup.kaesseli.backend.usergroup.UserGroup
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccountRepository : JpaRepository<Account, Long>{

   // fun getAccountsByUser(user: User): Set<Account>?
   // fun getAccountsByUserAndUserGroup(user: User, usergroup: UserGroup): Set<Account>?

    fun getAccountsByUserUserGroup(userUserGroup: UserUserGroup): Optional<Set<Account>>
}