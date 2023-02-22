package ch.levelup.kaesseli.backend.mobile

import ch.levelup.kaesseli.shared.LogedInUserDto
import ch.levelup.kaesseli.shared.NewTransactionDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/mobile")
class MobileController(
    private val mobileService: MobileService
) {
    @GetMapping("logedInUser/{email}")
    fun getLogedInUserByEmail(@PathVariable(value = "email") email: String): ResponseEntity<LogedInUserDto>? {
        val userO = mobileService.getLogedInUserByEmail(email);

        if (userO.isEmpty) {
            return ResponseEntity.notFound().build()
        }
        val user = userO.get()
        return ResponseEntity.ok(user);
    }

    @PostMapping("transactions")
    fun addTransaction(@RequestBody transactionDto: NewTransactionDto): ResponseEntity<Void> =
        mobileService.addTransaction(transactionDto)

   // set Token with Token String, email


}