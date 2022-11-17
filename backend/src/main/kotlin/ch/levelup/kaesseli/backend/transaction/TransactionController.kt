package ch.levelup.kaesseli.backend.transaction

import ch.levelup.kaesseli.backend.common.TransactionDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.stream.Stream

@RestController
@RequestMapping("/api/transactions")
class TransactionController (private val transactionService: TransactionService){

    @GetMapping
    fun getTransactions(): List<Transaction> = transactionService.getTransactions()

    @GetMapping("accountId/{accountId}")
    fun getTransactionsByAccountId(@PathVariable(value = "accountId") accountId: Long): Stream<TransactionDto>? {
        return transactionService.getTransactionsByAccount(accountId)
    }

    @GetMapping("/{id}")
    fun getTransactionById(@PathVariable(value = "id") transactionId: Long): ResponseEntity<Transaction> =
        transactionService.getTransactionById(transactionId)

    @PostMapping
    fun addTransaction(@RequestBody transaction: Transaction): ResponseEntity<Transaction> =
        transactionService.addTransaction(transaction)

    @PutMapping("/{id}")
    fun updateTransactionById(
        @PathVariable(value = "id") transactionId: Long,
        @RequestBody newTransaction: Transaction
    ): ResponseEntity<Transaction> =
        transactionService.putTransaction(transactionId, newTransaction)

    @DeleteMapping("/{id}")
    fun deleteTransaction(@PathVariable(value = "id") transactionId: Long): ResponseEntity<Void> =
        transactionService.deleteTransaction(transactionId)
}