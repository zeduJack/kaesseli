package ch.levelup.kaesseli.user

import ch.levelup.kaesseli.GatewayResponse
import ch.levelup.kaesseli.GenericError
import ch.levelup.kaesseli.PlatformDispatcher
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.json.Json

open class UserRepository {
    //private val client = HttpClient()
    private val networkScope = CoroutineScope(PlatformDispatcher)
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }

    suspend fun createUser(
        user: User?
    ): GatewayResponse<User, GenericError> {
        try {
            delay(2000)

            val testUser = User(
                id = 0,
                createdAt = Clock.System.now().toLocalDateTime(timeZone = TimeZone.UTC),
                updatedAt = Clock.System.now().toLocalDateTime(timeZone = TimeZone.UTC),
                version = 0,
                firstname = "Zeljko",
                lastname = "Dujmovic",
                username = "zedu",
                email = "zeljko.dujmovic83@gmail.com"
            )


            val response = client.post("http://20.250.109.29/api/user") {
                contentType(ContentType.Application.Json)
                setBody(testUser)
            }
            return GatewayResponse.createError(
                GenericError("Test error from creating user"),
                500,
                ""
            )

            return GatewayResponse.createSuccess(
                testUser,
                200,
                ""
            )
        } catch (e: Exception) {
            val error = e.message ?: "error"
            println(e)
            return GatewayResponse.createError(GenericError(error), 500, "")
        }
    }
}