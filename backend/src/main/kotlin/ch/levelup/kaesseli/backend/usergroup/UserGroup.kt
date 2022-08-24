package ch.levelup.kaesseli.backend.usergroup

import ch.levelup.kaesseli.backend.user.User
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "usergroup")
data class UserGroup(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val createdAt: LocalDateTime? = LocalDateTime.now(),
    val updatedAt: LocalDateTime? = LocalDateTime.now(),
    val version: Int,
    @ApiModelProperty(notes = "Provided name", required = true)
    val name: String,

    /*
    @ManyToMany(mappedBy = "user_usergroup")
    val users: Set<User>
     */
)