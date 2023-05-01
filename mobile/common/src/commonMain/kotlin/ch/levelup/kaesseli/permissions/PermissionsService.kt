package ch.levelup.kaesseli.permissions

import ch.levelup.kaesseli.GatewayResponse
import ch.levelup.kaesseli.GenericError
import ch.levelup.kaesseli.PlatformDispatcher
import ch.levelup.kaesseli.baseUrl
import ch.levelup.kaesseli.shared.PermissionsDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json

open class PermissionsService {
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

    suspend fun loadPermissions(loggedInUserId: Long, selectedGroupId: Long, selectedUserId: Long): GatewayResponse<PermissionsDto, GenericError> {
        try {
            val response = client.get("$baseUrl/api/mobile/permissions/$loggedInUserId?selectedGroupId=$selectedGroupId&selectedUserId=$selectedUserId")
            if (response.status != HttpStatusCode.OK) {
                return GatewayResponse.createError(GenericError("Problem beim Laden der Berechtigungen"), 500, "")
            }
            val gotUser = response.body<PermissionsDto>()

            return GatewayResponse.createSuccess(
                gotUser,
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
