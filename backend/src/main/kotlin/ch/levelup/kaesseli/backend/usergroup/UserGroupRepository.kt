package ch.levelup.kaesseli.backend.usergroup

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserGroupRepository : JpaRepository<UserGroup, Long> {

    fun getUserGroupById(id: Long): UserGroup
}
