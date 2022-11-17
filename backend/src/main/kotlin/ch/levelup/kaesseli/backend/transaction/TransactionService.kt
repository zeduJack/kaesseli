package ch.levelup.kaesseli.backend.transaction

import ch.levelup.kaesseli.backend.account.AccountService
import ch.levelup.kaesseli.backend.common.TransactionDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.stream.Stream


@Service
class TransactionService(
    private val transactionRepository: TransactionRepository,
    private val accountService: AccountService
) {

    fun getTransactions(): List<Transaction> = transactionRepository.findAll()

    fun getTransactionsByAccount(accountId: Long): Stream<TransactionDto>? {
        val account = accountService.getAccountDboById(accountId)
        return transactionRepository.getTransactionsByAccount(account)
            ?.stream()
            ?.map { transaction -> mapTransactionDto(transaction) }
    }

    private fun mapTransactionDto(transaction: Transaction) = TransactionDto(
        createdAt = transaction.createdAt,
        updatedAt = transaction.updatedAt,
        debit = transaction.debit,
        status = transaction.status,
        message = transaction.message,
        amount = transaction.amount
    )

    fun getTransactionById(transactionId: Long): ResponseEntity<Transaction> =
        transactionRepository.findById(transactionId)
            .map { transaction -> ResponseEntity.ok(transaction) }
            .orElse(ResponseEntity.notFound().build())

    fun addTransaction(transaction: Transaction): ResponseEntity<Transaction> =
        ResponseEntity.ok(transactionRepository.save(transaction))

    fun putTransaction(transactionId: Long, newTransaction: Transaction): ResponseEntity<Transaction> =
        transactionRepository.findById(transactionId)
            .map { currentTransaction ->
                val updated = transactionRepository.save(mapTransaction(newTransaction, currentTransaction))
                ResponseEntity.ok().body(updated)
            }
            .orElse(ResponseEntity.notFound().build())

    private fun mapTransaction(from: Transaction, to: Transaction) = to.copy(
        amount = from.amount,
        debit = from.debit,
        status = from.status,
        message = from.message
    )

    fun deleteTransaction(transactionId: Long): ResponseEntity<Void> =
        transactionRepository.findById(transactionId)
            .map { transaction ->
                transactionRepository.delete(transaction)
                ResponseEntity<Void>(HttpStatus.ACCEPTED)
            }
            .orElse(ResponseEntity.notFound().build())
}