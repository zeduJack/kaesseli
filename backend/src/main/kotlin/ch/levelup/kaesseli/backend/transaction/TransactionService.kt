package ch.levelup.kaesseli.backend.transaction

import io.swagger.models.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
class TransactionService (private val transactionRepository: TransactionRepository) {

    fun getTransactions(): List<Transaction> = transactionRepository.findAll()

    fun getTransactionById(transactionId: Long): ResponseEntity<Transaction> =
        transactionRepository.findById(transactionId).map { transaction ->
            ResponseEntity.ok(transaction)
        }.orElse(ResponseEntity.notFound().build())

    fun addTransaction(transaction: Transaction): ResponseEntity<Transaction> =
        ResponseEntity.ok(transactionRepository.save(transaction))

    fun putTransaction(transactionId: Long, newTransaction: Transaction): ResponseEntity<Transaction> =
        transactionRepository.findById(transactionId).map { currentTransaction ->
            val updateTransaction : Transaction = currentTransaction.copy(
                amount = newTransaction.amount,
                debit = newTransaction.debit,
                status = newTransaction.status,
                message = newTransaction.message
            )
            ResponseEntity.ok().body(transactionRepository.save(updateTransaction))
        }.orElse(ResponseEntity.notFound().build())

    fun deleteTransaction(transactionId: Long): ResponseEntity<Void> =
        transactionRepository.findById(transactionId).map { transaction ->
            transactionRepository.delete(transaction)
            ResponseEntity<Void>(HttpStatus.ACCEPTED)
        }.orElse(ResponseEntity.notFound().build())
}