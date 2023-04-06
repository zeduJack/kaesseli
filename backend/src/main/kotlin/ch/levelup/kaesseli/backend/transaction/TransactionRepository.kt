package ch.levelup.kaesseli.backend.transaction

import ch.levelup.kaesseli.backend.account.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TransactionRepository : JpaRepository<Transaction, Long> {

    fun getTransactionsByAccountOrderByCreatedAt(account: Account): Optional<Set<Transaction>>
    fun getTransactionsByAccountOrderByCreatedAt(account: Optional<Account>): Set<Transaction>?
}