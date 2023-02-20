package ch.levelup.kaesseli.backend.user

import ch.levelup.kaesseli.backend.common.UserUserGroup
import ch.levelup.kaesseli.backend.usergroup.UserGroup
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "\"user\"")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val createdAt: LocalDateTime? = LocalDateTime.now(),
    val updatedAt: LocalDateTime? = LocalDateTime.now(),
    val version: Int,

    val firstname: String,
    val lastname: String,
    val username: String?,
    val email: String,
    val token: String

    ){

    @OneToMany(mappedBy = "user")
    var userUserGroups: MutableSet<UserUserGroup> = mutableSetOf();
    /*
    @ManyToMany
    @JoinTable(
        name = "userusergroup",
        joinColumns = [JoinColumn(name = "userId")],
        inverseJoinColumns = [JoinColumn(name = "usergroupId")])
    var userGroups: Set<UserGroup> = setOf();*/

}