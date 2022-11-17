package ch.levelup.kaesseli.backend.account

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/accounts")
class AccountController (private val accountService: AccountService){

    @GetMapping
    fun getAccounts(): List<Account> = accountService.getAccounts()

    @GetMapping("/{id}")
    fun getAccountById(@PathVariable(value = "id") accountId: Long): ResponseEntity<Account> =
        accountService.getAccountById(accountId)

    @PostMapping
    fun addAccount(@RequestBody account: Account): ResponseEntity<Account> =
        accountService.addAccount(account)

    @PutMapping("/{id}")
    fun updateAccountById(
        @PathVariable(value = "id") accountId: Long,
        @RequestBody newAccount: Account
    ): ResponseEntity<Account> =
        accountService.putAccount(accountId, newAccount)

    @DeleteMapping("/{id}")
    fun deleteAccount(@PathVariable(value = "id") accountId: Long): ResponseEntity<Void> =
        accountService.deleteAccount(accountId)
}