package ch.levelup.kaesseli.backend

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserRecord
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BackendApplicationTests {
    //@Test
    fun testFirebaseUserManagement() {
        instantiateFirebaseApp()
        val testUid: String = "test-uid"
        FirebaseUserManagement().deleteFirebaseUser(testUid) //verify user does not exist
        createFirebaseUser(testUid)
        val success = FirebaseUserManagement().deleteFirebaseUser(testUid)
        if (success){
            println("User with uid '$testUid' has been successfully created and deleted.")
        }
    }

    private fun instantiateFirebaseApp() {
        val options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .setProjectId("kaesseli-18cf8")
                .build()

        FirebaseApp.initializeApp(options)
    }
    private fun createFirebaseUser(testUid: String){
        // https://firebase.google.com/docs/auth/admin/errors
        val request: UserRecord.CreateRequest = UserRecord.CreateRequest()
                .setUid(testUid)
                .setEmail("test_user@kaesseli.ch")
                .setPassword("secretPassword")
                .setDisabled(false)

        val userRecord = FirebaseAuth.getInstance().createUser(request)

        if (testUid != userRecord.uid) {
            println("Error, unexpected user created")
        }
        println("Successfully created new user: " + userRecord.uid)
    }
}
