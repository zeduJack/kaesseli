package ch.levelup.kaesseli.user

import ch.levelup.kaesseli.GatewayResponse
import ch.levelup.kaesseli.GenericError
import ch.levelup.kaesseli.PlatformDispatcher
import ch.levelup.kaesseli.baseUrl
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.CoroutineScope
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

    suspend fun logInUser(mail: String): GatewayResponse<UserDto, GenericError> {
        try {
            val response =
                client.get("$baseUrl/api/users/data/$mail")

            if (response.status != HttpStatusCode.OK) {
                return GatewayResponse.createError(GenericError("Problem beim Anmelden"), 500, "")
            }

            val gotUser = response.body<UserDto>()

            return GatewayResponse.createSuccess(
                gotUser,
                200,
                ""
            )

            /*
            return GatewayResponse.createError(
                GenericError("Test error from creating user"),
                500,
                ""
            )
            */


        } catch (e: Exception) {
            val error = e.message ?: "error"
            println(e)
            return GatewayResponse.createError(GenericError(error), 500, "")
        }
    }
}