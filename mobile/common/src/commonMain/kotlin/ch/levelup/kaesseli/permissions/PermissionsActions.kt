package ch.levelup.kaesseli.permissions

import ch.levelup.kaesseli.shared.PermissionsDto


class PermissionsActions {
    data class SetPermissions(val permissions: PermissionsDto)
}
