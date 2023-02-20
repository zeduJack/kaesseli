package ch.levelup.kaesseli.account

import ch.levelup.kaesseli.shared.AccountDto


fun accountReducer(state: AccountDto, action: Any) =
    when (action) {
        is AccountActions.SetAccount -> action.account
        else -> state
    }
