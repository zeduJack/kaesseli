package ch.levelup.kaesseli.backend.user

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

    /*
    @ManyToMany(mappedBy = "user_usergroup")
    @JoinTable(name = "user_usergroup")
    val usergroups: Set<UserGroup>*/
)