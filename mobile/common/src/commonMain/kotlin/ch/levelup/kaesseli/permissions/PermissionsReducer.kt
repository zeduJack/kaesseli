package ch.levelup.kaesseli.permissions

import ch.levelup.kaesseli.shared.PermissionsDto


fun permissionsReducer(state: PermissionsDto, action: Any) =
    when (action) {
        is PermissionsActions.SetPermissions -> action.permissions
        else -> state
    }
