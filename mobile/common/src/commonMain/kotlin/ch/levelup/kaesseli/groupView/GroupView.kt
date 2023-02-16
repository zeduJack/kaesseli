package ch.levelup.kaesseli.groupView

import ch.levelup.kaesseli.user.UserGroupDto

data class GroupView(
    val title: String = "Gruppen",
    val groups: Set<Group> = setOf()
)

data class Group(
    val userGroupDto: UserGroupDto = UserGroupDto(),
    var description: String = ""
)


