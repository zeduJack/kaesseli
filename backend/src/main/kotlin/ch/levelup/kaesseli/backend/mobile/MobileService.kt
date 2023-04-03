package ch.levelup.kaesseli.backend.mobile

import ch.levelup.kaesseli.backend.account.Account
import ch.levelup.kaesseli.backend.account.AccountRepository
import ch.levelup.kaesseli.backend.common.Role
import ch.levelup.kaesseli.backend.common.UserUserGroup
import ch.levelup.kaesseli.backend.firebase.FirebaseService
import ch.levelup.kaesseli.backend.transaction.Transaction
import ch.levelup.kaesseli.backend.transaction.TransactionRepository
import ch.levelup.kaesseli.backend.transaction.TransactionService
import ch.levelup.kaesseli.backend.user.User
import ch.levelup.kaesseli.backend.user.UserRepository
import ch.levelup.kaesseli.backend.usergroup.UserGroup
import ch.levelup.kaesseli.shared.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@Service
class MobileService(
    private val userRepository: UserRepository,
    private val accountRepository: AccountRepository,
    private val transActionsRepository: TransactionRepository,
    private val transactionService: TransactionService,
    private val firebaseService: FirebaseService
) {

    fun getLogedInUserByEmail(email: String): Optional<LogedInUserDto> {
        val userOptional = userRepository.getUserByEmail(email)

        if (userOptional.isPresent) {
            val user = userOptional.get()
            val logedInUser = mapUserDto(user)

            logedInUser.groupLabel = "Gruppen von ${user.firstname}"

            user.userUserGroups.forEach { userUserGroup ->

                val uGroup = mapUserGroupDto(userUserGroup.userGroup)

                val members: MutableSet<UserGroupMemberDto> = mutableSetOf()

                userUserGroup.userGroup.userUserGroups.map { userUserGroup ->
                    val member = mapUserGroupMemberDto(userUserGroup.user)
                    member.accounts = getAccountsForUserWithinGroup(userUserGroup)
                    member.accountsList = member.accounts.toList()
                    member.sumOfAccountsLabel = getSumOfAccounts(member.accounts)
                    member.accountsLabel = "Konten von ${member.firstname}"
                    member.paymentLabel = "Zahlung an ${member.firstname}"
                    member.amoutnLabel = "Betrag"
                    member.messageLabel = "Mitteilung"
                    members.add(member)
                }
                uGroup.members.addAll(members)

                uGroup.membersDescription = "${members.size} Mitglieder"
                uGroup.membersTitle = "Mitglieder von ${uGroup.name}"
                //  uGroup.accounts = getAccountsForUserWithinGroup(userUserGroup);
                logedInUser.groups.add(uGroup)
                logedInUser.groupsList = logedInUser.groups.toList()
            }

            return Optional.of(logedInUser)
        }
        return Optional.empty()
    }

    fun updateUserToken(email: String, token: String): ResponseEntity<User>? {
        return userRepository.getUserByEmail(email).map { currentUser ->
            val updateUser: User = currentUser.copy(
                firstname = currentUser.firstname,
                lastname = currentUser.lastname,
                email = currentUser.email,
                username = currentUser.username,
                token = token
            )
            ResponseEntity.ok().body(userRepository.save(updateUser))
        }.orElse(ResponseEntity.notFound().build())
    }

    fun addTransaction(newTransactionDto: NewTransactionDto): ResponseEntity<Void> {
        val userOptional = userRepository.getUserByEmail(newTransactionDto.logedInUserEmail)
        val accountOptional = accountRepository.findById(newTransactionDto.accountId)

        if (userOptional.isEmpty || accountOptional.isEmpty) {
            return ResponseEntity.notFound().build()
        }

        val user = userOptional.get()
        val account = accountOptional.get()
        val amount = BigDecimal(newTransactionDto.amount)

        val transaction = Transaction(
            version = 0,
            amount = amount,
            user = user,
            account = account,
            debit = newTransactionDto.debit,
            status = "recived",
            message = newTransactionDto.message,
            resultingSaldo = BigDecimal(0)
        )
        val transResponseEntity = transactionService.addTransaction(transaction)
        if(transResponseEntity.statusCode == HttpStatus.OK){
            var trans = transResponseEntity.body;
            if (trans != null) {
                performeTransactionOnAccount(trans, account)
            }
        }

        val token = account.userUserGroup.user.token
        if(token != null && token != "") {
            val text = user.firstname + " has send you " + amount
            firebaseService.pushMessage(token, text, text)
        }
        return ResponseEntity.ok().build()
    }

    private fun performeTransactionOnAccount(trans: Transaction, account: Account){
        if(trans.status == "recived"){
            if(trans.debit){
                account.saldo -= trans.amount
            }else {
                account.saldo += trans.amount
            }
            account.updatedAt = LocalDateTime.now()
            accountRepository.save(account)
            trans.resultingSaldo = account.saldo
            transactionService.setTransProcessed(trans)
        }
    }

    private fun getSumOfAccounts(accounts: MutableSet<AccountDto>): String {
        var sum = 0L
        for (account in accounts) {
            sum += account.saldo
        }

        return "CHF $sum"
    }

    private fun getAccountsForUserWithinGroup(userUserGroup: UserUserGroup): MutableSet<AccountDto> {
        val accounts: MutableSet<AccountDto> = mutableSetOf()
        val dboAccounts = accountRepository.getAccountsByUserUserGroup(userUserGroup)
        if (dboAccounts.isPresent) {
            dboAccounts.get().forEach { accountDbo ->
                val accountDto = mapAccount(accountDbo)
                accountDto.transactions = getTransactionsForAccount(accountDbo)
                accountDto.transactionsList = accountDto.transactions.toList()
                accountDto.saldoLabel = "CHF ${accountDbo.saldo}"
                accountDto.accountLabel =
                    "${accountDbo.displayName} von ${userUserGroup.user.firstname}"
                accountDto.kontostandLabel = "Kontostand: ${accountDbo.saldo} CHF"
                accountDto.paymentAccountDescription =
                    "Der Betrag wird dem Konto '${accountDbo.displayName}' überwiesen"

                accounts.add(accountDto)
            }
        }
        return accounts
    }


    private fun getTransactionsForAccount(account: Account): MutableSet<TransactionDto> {
        val transactions: MutableSet<TransactionDto> = mutableSetOf()

        val transactionsDbo = transActionsRepository.getTransactionsByAccount(account)
        transactions.addAll(transactionsDbo.get().map { transactionDbo ->
            mapTransactionDto(transactionDbo)
        })
        return transactions
    }

    private fun mapTransactionDto(transaction: Transaction) = TransactionDto(
        id = transaction.id ?: 0L,
        createdAt = transaction.createdAt.toString(),
        amount = transaction.amount.toString(),
        username = transaction.user.username.toString(),
        userFirstname = transaction.user.firstname,
        userLastname = transaction.user.lastname,
        debit = transaction.debit,
        message = transaction.message,
        status = transaction.status,
        resultingSaldo = transaction.resultingSaldo.toString(),
        chartLabel = if (transaction.debit) "-" + transaction.amount else "+" + transaction.amount
    )

    private fun mapUserDto(user: User) = LogedInUserDto(
        id = user.id ?: 0L,
        firstname = user.firstname,
        lastname = user.lastname,
        email = user.email,
        username = user.username ?: ""
    )

    private fun mapUserGroupDto(userGroup: UserGroup) = LogedInUserUserGroupDto(
        id = userGroup.id ?: 0L,
        name = userGroup.name
    )

    private fun mapUserGroupMemberDto(user: User) = UserGroupMemberDto(
        id = user.id ?: 0L,
        username = user.username ?: "",
        firstname = user.firstname,
        lastname = user.lastname
    )

    private fun mapRole(role: Role) = RoleDto(
        id = role.id ?: 0L,
        name = role.name
    )

    private fun mapAccount(account: Account) = AccountDto(
        id = account.id ?: 0L,
        type = account.type,
        saldo = account.saldo.toLong(),
        displayName = account.displayName
    )
}