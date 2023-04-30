package ch.levelup.kaesseli.backend.mobile

import ch.levelup.kaesseli.shared.PermissionsDto
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
    fun getLogedInUserByEmail(@PathVariable(value = "email") email: String, @RequestParam(required = true) token: String?): ResponseEntity<LogedInUserDto>? {
        if (! token.isNullOrEmpty()) {
            mobileService.updateUserToken(email, token)
        }

        val userO = mobileService.getLogedInUserByEmail(email)

        if (userO.isEmpty) {
            return ResponseEntity.notFound().build()
        }
        val user = userO.get()
        return ResponseEntity.ok(user)
    }

    @PostMapping("transactions")
    fun addTransaction(@RequestBody transactionDto: NewTransactionDto): ResponseEntity<Void> =
        mobileService.addTransaction(transactionDto)

    @GetMapping("permissions/{id}")
    fun getAccessControl(
        @PathVariable(value = "id") loggedInUserId: Long,
        @RequestParam(required = true) selectedGroupId: Long,
        @RequestParam(required = true) selectedUserId: Long,
    ): ResponseEntity<PermissionsDto> {

        val permissionsStatus = mobileService.getPermissions(loggedInUserId, selectedGroupId, selectedUserId)

        return ResponseEntity.ok(permissionsStatus)
    }
   // set Token with Token String, email
}
