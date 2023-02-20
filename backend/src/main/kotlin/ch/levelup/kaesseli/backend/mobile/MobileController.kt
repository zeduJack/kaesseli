package ch.levelup.kaesseli.backend.mobile

import ch.levelup.kaesseli.shared.LogedInUserDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/mobile")
class MobileController(
    private val mobileService: MobileService
) {
    @GetMapping("logedInUser/{email}")
    fun getLogedInUserByEmail(@PathVariable(value = "email") email: String): ResponseEntity<LogedInUserDto>? {
        var userO = mobileService.getLogedInUserByEmail(email);

        if (userO.isEmpty) {
            return ResponseEntity.notFound().build();
        }
        var user = userO.get()
        return ResponseEntity.ok(user);
    }

   // set Token with Token String, email


}