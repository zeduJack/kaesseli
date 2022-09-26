package ch.levelup.kaesseli.backend.account

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional(Transactional.TxType.MANDATORY)
interface AccountRepository : JpaRepository<Account, Long>{
}