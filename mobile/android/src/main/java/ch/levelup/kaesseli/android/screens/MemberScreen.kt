package ch.levelup.kaesseli.android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch.levelup.kaesseli.state.AppState
import ch.levelup.kaesseli.state.Store
import ch.levelup.kaesseli.ScreenNavigation
import ch.levelup.kaesseli.account.AccountActions
import ch.levelup.kaesseli.navigation.Navigation
import ch.levelup.kaesseli.navigation.NavigationActions
import ch.levelup.kaesseli.state.CreateUserGroup
import ch.levelup.kaesseli.user.AccountDto

@Composable
fun MemberScreen(
    appState: AppState
) {
    Column(modifier = Modifier.padding(16.dp)) {
        val postfix: String = if(appState.member.firstname == ""){
            ""
        } else {
            " von " + appState.member.firstname
        }

        Text(
            text = "Konten$postfix",
            style = TextStyle(fontSize = 18.sp)
        )

        val listModifier = Modifier
            .background(Color.White)
            .padding(10.dp)

        LazyColumn(modifier = listModifier) {
            items(appState.member.accounts.toList()) { account: AccountDto ->
                TextButton(modifier = Modifier.fillMaxWidth(), onClick = { setSelectedMember(account)}) {
                    Row {
                        Text(account.displayName)
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }

        Button(onClick = { Store.instance.dispatch(CreateUserGroup(userGroupName = "Neues Mitglied")) }) {
            Text(text = "Neues Mitglied hinzufügen")
        }
    }
}

private fun setSelectedMember(selectedAccount: AccountDto){
    Store.instance.dispatch(AccountActions.SetAccount(selectedAccount))
    Store.instance.dispatch(NavigationActions.SetNavigation(Navigation(ScreenNavigation.BalanceScreen.route)))
}