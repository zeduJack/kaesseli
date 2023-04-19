package ch.levelup.kaesseli.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch.levelup.kaesseli.state.AppState
import ch.levelup.kaesseli.state.Store
import ch.levelup.kaesseli.transaction.TransactionNetworkThunks
import java.text.DecimalFormat


@Composable
fun CreditScreen(appState: AppState) {

    LaunchedEffect(true) {
        Store.instance.dispatch(TransactionNetworkThunks.getTransactions())
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            //text = appState.account.displayName,
            text = appState.selectedAccount.creditAccountDescription,
            style = TextStyle(fontSize = 18.sp),
            modifier = Modifier.padding(0.dp, 18.dp)
        )

        var dirty by remember { mutableStateOf(false) }
        val amountText = remember { mutableStateOf("") }

        var message by remember { mutableStateOf("") }

        val symbols = DecimalFormat().decimalFormatSymbols
        val thousandsSeparator = symbols.groupingSeparator
        val decimalSeparator = symbols.decimalSeparator

        val regex = Regex("^[0-9]*\\.?\\d{0,2}\$")
        val modifier = Modifier.padding(0.dp, 3.dp, 0.dp, 0.dp)


        TextField(
            modifier = modifier,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = amountText.value,
            isError = dirty && amountText.value.isEmpty(),
            label = { Text(text = appState.selectedMember.amountLabel) },
            onValueChange = { newText ->
                if (regex.matches(newText)) {
                    amountText.value = newText
                }
            }
        )

        TextField(
            modifier = modifier,
            value = message,
            label = { Text(text = appState.selectedMember.messageLabel) },
            onValueChange = { newText -> message = newText }
        )

        Button(onClick = {
            dirty = true
            if (amountText.value.isNotEmpty()) {
                Store.instance.dispatch(TransactionNetworkThunks.addTransaction(amountText.value.toLong(), message, false))
            }

        }) {
            Text(text = appState.selectedAccount.confirmationButtonLabel)
        }
    }
}