package ch.levelup.kaesseli.backend.scheduler

import io.swagger.models.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class SchedulerService(private val schedulerRepository: SchedulerRepository) {

    fun getSchedulers(): List<Scheduler> = schedulerRepository.findAll();

    fun getSchedulerById(schedulerId: Long): ResponseEntity<Scheduler> =
        schedulerRepository.findById(schedulerId).map { scheduler ->
            ResponseEntity.ok(scheduler)
        }.orElse(ResponseEntity.notFound().build())

    fun addScheduler(scheduler: Scheduler): ResponseEntity<Scheduler> =
        ResponseEntity.ok(schedulerRepository.save(scheduler))

    fun putScheduler(schedulerId: Long, newScheduler : Scheduler): ResponseEntity<Scheduler> =
        schedulerRepository.findById(schedulerId).map { currentScheduler ->
            val updateScheduler: Scheduler = currentScheduler.copy(
                scheduleTime = newScheduler.scheduleTime,
                amount = newScheduler.amount,
                message = newScheduler.message
            )
            ResponseEntity.ok().body(schedulerRepository.save(updateScheduler))
        }.orElse(ResponseEntity.notFound().build())

    fun deleteScheduler(schedulerId: Long): ResponseEntity<Void> =
        schedulerRepository.findById(schedulerId).map { scheduler ->
            schedulerRepository.delete(scheduler)
            ResponseEntity<Void>(HttpStatus.ACCEPTED)
        }.orElse(ResponseEntity.notFound().build())
}