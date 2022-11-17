package ch.levelup.kaesseli.backend.user

import ch.levelup.kaesseli.backend.account.Account
import ch.levelup.kaesseli.backend.account.AccountService
import ch.levelup.kaesseli.backend.common.AccountDto
import ch.levelup.kaesseli.backend.common.MemberDto
import ch.levelup.kaesseli.backend.common.UserDto
import ch.levelup.kaesseli.backend.common.UserGroupDto
import ch.levelup.kaesseli.backend.usergroup.UserGroup
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class UserService(
    private val userRepository: UserRepository,
    private val accountService: AccountService) {

    fun getUsers(): List<User> = userRepository.findAll()

    fun getUserByEmail(email: String): ResponseEntity<User>? {
        return userRepository.getUserByEmail(email)
            .map { user -> ResponseEntity.ok(user) }
            .orElse(ResponseEntity.notFound().build())
    }

    fun getUsers(userGroup: UserGroup?): Set<User>? = userRepository.getUsersByUserGroups(userGroup)

    fun getAccounts(user: User): Set<Account>? = accountService.getAccountsByUserGroup(user)

    fun getUserDboById(userId: Long): Optional<User> = userRepository.findById(userId)

    fun getUserById(userId: Long): ResponseEntity<User> =
        userRepository.findById(userId)
            .map { user -> ResponseEntity.ok(user) }
            .orElse(ResponseEntity.notFound().build())

    fun addUser(user: User): ResponseEntity<User> = ResponseEntity.ok(userRepository.save(user))

    fun putUser(userId: Long, newUser: User): ResponseEntity<User> =
        userRepository.findById(userId).map { currentUser ->
            val updateUser: User = currentUser.copy(
                firstname = newUser.firstname,
                lastname = newUser.lastname,
                email = newUser.email,
                username = newUser.username
            )
            ResponseEntity.ok().body(userRepository.save(updateUser))
        }.orElse(ResponseEntity.notFound().build())

    fun deleteUser(userId: Long): ResponseEntity<Void> =
        userRepository.findById(userId).map { user ->
            userRepository.delete(user)
            ResponseEntity<Void>(HttpStatus.ACCEPTED)
        }.orElse(ResponseEntity.notFound().build())

    fun getUserDtoByEmail(email: String): ResponseEntity<UserDto>? {
        return userRepository.getUserByEmail(email)
            .map { user -> ResponseEntity.ok().body(mapUserDto(user)) }
            .orElse(ResponseEntity.notFound().build())
    }

    private fun mapUserDto(user: User) = UserDto(
        firstname = user.firstname,
        lastname = user.lastname,
        username = user.username,
        email = user.email,
        userGroups = mapUserGroups(user.userGroups)
    )

    private fun mapUserGroups(userGroups: Set<UserGroup>?): Set<UserGroupDto>? {
        return userGroups
            ?.stream()
            ?.map { userGroup -> mapUserGroupDto(userGroup) }
            ?.collect(Collectors.toSet())
    }

    private fun mapUserGroupDto(userGroup: UserGroup) = UserGroupDto(
        name = userGroup.name,
        members = mapMembers(userGroup)
    )

    private fun mapMembers(userGroup: UserGroup?): Set<MemberDto>? {
        return getUsers(userGroup)
            ?.stream()
            ?.map { user -> mapMemberDto(user) }
            ?.collect(Collectors.toSet())
    }

    private fun mapMemberDto(user: User) = MemberDto(
        firstname = user.firstname,
        lastname = user.lastname,
        accounts = mapAccounts(getAccounts(user))
    )

    private fun mapAccounts(accounts: Set<Account>?): Set<AccountDto>? {
        return accounts?.stream()
            ?.map { account -> mapAccountDto(account) }
            ?.collect(Collectors.toSet())
    }

    private fun mapAccountDto(account: Account) = AccountDto(
        id = account.id,
        type = account.type,
        saldo = account.saldo,
        displayName = account.displayName
    )
}