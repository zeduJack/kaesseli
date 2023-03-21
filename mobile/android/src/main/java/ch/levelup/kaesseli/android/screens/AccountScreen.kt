package ch.levelup.kaesseli.android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch.levelup.kaesseli.ScreenNavigation
import ch.levelup.kaesseli.android.components.KsH1
import ch.levelup.kaesseli.android.components.KsHeaderRow
import ch.levelup.kaesseli.android.components.KsMinusButton
import ch.levelup.kaesseli.android.components.KsPlusButton
import ch.levelup.kaesseli.navigation.Navigation
import ch.levelup.kaesseli.navigation.NavigationActions
import ch.levelup.kaesseli.state.AppState
import ch.levelup.kaesseli.state.Store
import ch.levelup.kaesseli.transaction.TransactionDto
import ch.levelup.kaesseli.transaction.TransactionNetworkThunks

@Composable
fun AccountScreen(appState: AppState) {

    LaunchedEffect(true) {
        Store.instance.dispatch(TransactionNetworkThunks.getTransactions())
    }

    Column(modifier = Modifier.padding(16.dp)) {

        KsHeaderRow {
            KsH1(text = appState.selectedAccount.accountLabel)
        }
        KsHeaderRow {
            KsMinusButton(onClick = {
                Store.instance.dispatch(
                    NavigationActions.SetNavigation(
                        Navigation(ScreenNavigation.DebitScreen.route)
                    )
                )
            })
            KsPlusButton(onClick = {
                Store.instance.dispatch(
                    NavigationActions.SetNavigation(
                        Navigation(ScreenNavigation.CreditScreen.route)
                    )
                )
            })
        }

        Text(
            //text = appState.account.displayName,
            text = appState.selectedAccount.kontostandLabel,
            style = TextStyle(fontSize = 18.sp),
            modifier = Modifier.padding(0.dp, 18.dp)
        )

        val listModifier = Modifier
            .background(Color.White)
            .padding(10.dp)

        // Each cell of a column must have the same weight.
        val column1Weight = .7f // 70%
        val column2Weight = .3f // 30%
        val green = Color(0, 128, 0)

        LazyColumn(modifier = listModifier) {
            items(appState.currentAccountTransactions.toList()) { transaction: TransactionDto ->
                Row {
                    Text(transaction.message,
                        Modifier.weight(column1Weight))
                    Text(
                        text = (if (transaction.debit) "- " else "+ ") + transaction.amount.toString(),
                        textAlign = TextAlign.Right,
                        modifier = Modifier.weight(column2Weight),
                        color = (if (transaction.debit) Color.Black else green)
                    )
                }
                Divider()
            }
        }
    }
}