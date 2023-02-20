package ch.levelup.kaesseli.groupView

import ch.levelup.kaesseli.shared.LogedInUserUserGroupDto


data class GroupView(
    val title: String = "Gruppen",
    val groups: Set<Group> = setOf()
)

data class Group(
    val userGroupDto: LogedInUserUserGroupDto = LogedInUserUserGroupDto(),
    var description: String = ""
)


