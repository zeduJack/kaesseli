package ch.levelup.kaesseli.backend.common

import ch.levelup.kaesseli.backend.user.User
import ch.levelup.kaesseli.backend.usergroup.UserGroup
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "\"userusergrouprole\"")
data class UserUserGroupRole (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val createdAt: LocalDateTime? = LocalDateTime.now(),
    val updatedAt: LocalDateTime? = LocalDateTime.now(),
    val version: Int,

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    val user: User,
    @ManyToOne
    @JoinColumn(name="usergroup_id", nullable=false)
    val usergroup: UserGroup,
    @ManyToOne
    @JoinColumn(name="role_id", nullable=false)
    val role: Role
    )