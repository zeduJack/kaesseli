package ch.levelup.kaesseli.backend.common

import ch.levelup.kaesseli.backend.account.Account
import ch.levelup.kaesseli.backend.user.User
import ch.levelup.kaesseli.backend.usergroup.UserGroup
import ch.levelup.kaesseli.backend.usergroup.UserGroupRepository
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "\"userusergroup\"")
data class UserUserGroup(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val createdAt: LocalDateTime? = LocalDateTime.now(),
    val updatedAt: LocalDateTime? = LocalDateTime.now(),
    val version: Int,

    //val usergroupId: Long,
    //val userId: Long,
    @ManyToOne
    val userGroup: UserGroup,

    @ManyToOne
    val user: User
){

//    @OneToMany
  //  var accounts: MutableSet<Account> = mutableSetOf();
}