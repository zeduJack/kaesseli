package ch.levelup.kaesseli.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ch.levelup.kaesseli.ScreenNavigation
import ch.levelup.kaesseli.account.AccountActions
import ch.levelup.kaesseli.android.components.KsListItem
import ch.levelup.kaesseli.android.listModifier
import ch.levelup.kaesseli.navigation.Navigation
import ch.levelup.kaesseli.navigation.NavigationActions
import ch.levelup.kaesseli.shared.AccountDto
import ch.levelup.kaesseli.state.AppState
import ch.levelup.kaesseli.state.Store

@Composable
fun MemberScreen(
    appState: AppState
) {
    Column(modifier = Modifier.padding(16.dp)) {

        LazyColumn(modifier = listModifier) {
            items(appState.selectedMember.accounts.toList()) { account: AccountDto ->
                KsListItem(
                    text = account.displayName,
                    onClick = {setSelectedMember(account)},
                    detailText = account.saldoLabel
                )
            }
        }
    }
}

private fun setSelectedMember(selectedAccount: AccountDto){
    Store.instance.dispatch(AccountActions.SetAccount(selectedAccount))
    Store.instance.dispatch(NavigationActions.SetNavigation(Navigation(ScreenNavigation.AccountScreeen.route)))
}