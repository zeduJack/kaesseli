package ch.levelup.kaesseli.backend.mobile

import ch.levelup.kaesseli.backend.user.UserService
import ch.levelup.kaesseli.backend.usergroup.UserGroupService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/mobile")
class MobileController(
    private val mobileService: MobileService
) {

// Get Logged In User Data
    // By username
    // load usergroups for logedInUser
        // load all members with account data if user can access account

}