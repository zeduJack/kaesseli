package ch.levelup.kaesseli.backend.user

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

    ){
    @ManyToMany
    @JoinTable(
        name = "userusergroup",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "usergroup_id")])
    var userGroups: Set<UserGroup> = setOf();

}