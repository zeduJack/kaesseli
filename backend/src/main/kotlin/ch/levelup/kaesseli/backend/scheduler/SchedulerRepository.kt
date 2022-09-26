package ch.levelup.kaesseli.backend.scheduler

import ch.levelup.kaesseli.backend.account.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional(Transactional.TxType.MANDATORY)
interface SchedulerRepository : JpaRepository<Scheduler, Long> {
}