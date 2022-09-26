package ch.levelup.kaesseli.backend.scheduler

import ch.levelup.kaesseli.backend.scheduler.Scheduler
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/scheduler")
class SchedulerController (private val schedulerService: SchedulerService) {

    @GetMapping
    fun getSchedulers(): List<Scheduler> = schedulerService.getSchedulers()

    @GetMapping("/{id}")
    fun getSchedulerById(@PathVariable(value = "id") schedulerId: Long): ResponseEntity<Scheduler> =
        schedulerService.getSchedulerById(schedulerId)

    @PostMapping
    fun addScheduler(@RequestBody scheduler: Scheduler): ResponseEntity<Scheduler> =
        schedulerService.addScheduler(scheduler)

    @PutMapping("/{id}")
    fun updateSchedulerById(
        @PathVariable(value = "id") schedulerId: Long,
        @RequestBody newScheduler: Scheduler
    ): ResponseEntity<Scheduler> =
        schedulerService.putScheduler(schedulerId, newScheduler)

    @DeleteMapping("/{id}")
    fun deleteScheduler(@PathVariable(value = "id") schedulerId: Long): ResponseEntity<Void> =
        schedulerService.deleteScheduler(schedulerId)
}