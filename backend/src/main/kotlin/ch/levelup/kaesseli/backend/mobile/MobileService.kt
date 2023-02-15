package ch.levelup.kaesseli.backend.mobile

import ch.levelup.kaesseli.backend.account.Account
import ch.levelup.kaesseli.backend.account.AccountRepository
import ch.levelup.kaesseli.backend.common.Role
import ch.levelup.kaesseli.backend.common.UserUserGroup
import ch.levelup.kaesseli.backend.user.User
import ch.levelup.kaesseli.backend.user.UserRepository
import ch.levelup.kaesseli.backend.usergroup.UserGroup
import ch.levelup.kaesseli.shared.*

import org.springframework.stereotype.Service
import java.util.*

@Service
class MobileService(
    private val userRepository: UserRepository,
    private val accountRepository: AccountRepository
    ) {

    fun getLogedInUserByEmail(email: String): Optional<LogedInUserDto> {
        val userOptional = userRepository.getUserByEmail(email);

        if (userOptional.isPresent) {
            val user = userOptional.get();
            var logedInUser = mapUserDto(user);

            user.userUserGroups.forEach { userUserGroup ->

                val uGroup = mapUserGroupDto(userUserGroup.userGroup);

                uGroup.members.addAll(userUserGroup.userGroup.userUserGroups.map { userUserGroup ->
                    mapUserGroupMemberDto(userUserGroup.user)
                })
                uGroup.accounts = getAccountsForUserWithinGroup(userUserGroup);
                logedInUser.groups.add(uGroup)

            }

            return Optional.of(logedInUser)
        }
        return Optional.empty()
    }

    private fun getAccountsForUserWithinGroup(userUserGroup: UserUserGroup): MutableSet<AccountDto> {
        val accounts: MutableSet<AccountDto> = mutableSetOf();
        val dboAccounts = accountRepository.getAccountsByUserUserGroup(userUserGroup);
        if(dboAccounts.isPresent){
            dboAccounts.get().forEach { accountDbo ->
                accounts.add(mapAccount(accountDbo))
            };
        }
        return accounts;
    }

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
        saldo = account.saldo.longValueExact(),
        displayName = account.displayName
    )

}