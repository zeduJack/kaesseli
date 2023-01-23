package ch.levelup.kaesseli.android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        Text(
            text = appState.account.displayName,
            style = TextStyle(fontSize = 18.sp),
            modifier = Modifier.padding(0.dp, 18.dp)
        )
        Text(
            text = "Kontostand: " + appState.account.saldo,
            style = TextStyle(fontSize = 18.sp)
        )

        Text(
            //text = appState.account.displayName,
            text = "Transaktionen:",
            style = TextStyle(fontSize = 18.sp),
            modifier = Modifier.padding(0.dp, 18.dp)
        )

        val listModifier = Modifier
            .background(Color.White)
            .padding(10.dp)

        // Each cell of a column must have the same weight.
        val column1Weight = .7f // 70%
        val column2Weight = .3f // 30%

        LazyColumn(modifier = listModifier) {
            items(appState.currentAccountTransactions.toList()) { transaction: TransactionDto ->
                Row {
                    Text(transaction.message,
                    Modifier.weight(column1Weight))
                    Text(
                        text = (if (transaction.debit) "- " else "+ ") + transaction.amount.toString(),
                        textAlign = TextAlign.Right,
                        modifier = Modifier.weight(column2Weight),
                        color = (if (transaction.debit) Color.Black else Color.Green)
                    )
                }
            }
        }
    }
}