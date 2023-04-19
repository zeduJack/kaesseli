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
import ch.levelup.kaesseli.android.components.KsH1
import ch.levelup.kaesseli.android.components.KsHeaderRow
import ch.levelup.kaesseli.state.AppState
import ch.levelup.kaesseli.state.Store
import ch.levelup.kaesseli.transaction.TransactionNetworkThunks
import java.text.DecimalFormat


@Composable
fun DebitScreen(appState: AppState) {

    LaunchedEffect(true) {
        Store.instance.dispatch(TransactionNetworkThunks.getTransactions())
    }

    Column(modifier = Modifier.padding(16.dp)) {
        KsHeaderRow {
            KsH1(text = appState.selectedMember.debitLabel)
        }

        Text(
            text = appState.selectedAccount.debitAccountDescription,
            style = TextStyle(fontSize = 18.sp),
            modifier = Modifier.padding(0.dp, 18.dp)
        )

        /*
        val checked = remember { mutableStateOf(false) }
        Row() {
            Switch(
                checked = checked.value,
                onCheckedChange = { checked.value = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = CristalBlue,
                    checkedTrackColor = CristalBlue,
                    //uncheckedThumbColor = Yellow,
                    //uncheckedTrackColor= Yellow,
                ),
            )

            Text(
                text = appState.selectedAccount.paymentToggleIsDebit,
                style = TextStyle(fontSize = 18.sp),
                modifier = Modifier.padding(5.dp, 18.dp)
            )
            //Spacer(Modifier.weight(1f))
        }
        */

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
                Store.instance.dispatch(TransactionNetworkThunks.addTransaction(amountText.value.toLong(), message, true))
            }

        }) {
            Text(text = appState.selectedAccount.confirmationButtonLabel)
        }
    }
}