package ch.levelup.kaesseli.backend.common

import ch.levelup.kaesseli.backend.user.User
import ch.levelup.kaesseli.backend.usergroup.UserGroup
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
    @ManyToOne
    @JoinColumn(name = "usergroup_id", nullable = false)
    val userGroup: UserGroup,

    //val userId: Long,
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User
) {

   /* @OneToMany(mappedBy="userUserGroup")
    var accounts: MutableSet<Account> = mutableSetOf();

    */
}