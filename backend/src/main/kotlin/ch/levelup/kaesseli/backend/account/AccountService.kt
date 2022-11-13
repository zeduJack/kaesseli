package ch.levelup.kaesseli.backend.account

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AccountService (private val accountRepository: AccountRepository) {

    fun getAccounts(): List<Account> = accountRepository.findAll();

    fun getAccountById(accountId: Long): ResponseEntity<Account> =
        accountRepository.findById(accountId).map { account ->
            ResponseEntity.ok(account)
        }.orElse(ResponseEntity.notFound().build())

    fun addAccount(account: Account): ResponseEntity<Account> =
        ResponseEntity.ok(accountRepository.save(account))

    fun putAccount(accountId: Long, newAccount: Account): ResponseEntity<Account> =
        accountRepository.findById(accountId).map { currentAccount ->
            val updateAccount: Account = currentAccount.copy(
                type = newAccount.type,
                saldo = newAccount.saldo,
                displayName = newAccount.displayName
            )
            ResponseEntity.ok().body(accountRepository.save(updateAccount))
        }.orElse(ResponseEntity.notFound().build())

    fun deleteAccount(accountId: Long): ResponseEntity<Void> =
        accountRepository.findById(accountId).map { account ->
            accountRepository.delete(account)
            ResponseEntity<Void>(HttpStatus.ACCEPTED)
        }.orElse(ResponseEntity.notFound().build())
}