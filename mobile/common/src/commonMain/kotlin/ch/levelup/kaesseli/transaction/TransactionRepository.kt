package ch.levelup.kaesseli.transaction

import ch.levelup.kaesseli.GatewayResponse
import ch.levelup.kaesseli.GenericError
import ch.levelup.kaesseli.PlatformDispatcher
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json

open class TransactionRepository {
    private val networkScope = CoroutineScope(PlatformDispatcher)
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }

    suspend fun logInUser(accountId: Int): GatewayResponse<Set<TransactionDto>, GenericError> {
        try {
            val response =
                client.get("http://20.250.109.29/api/transactions/accountId/$accountId")

            if (response.status != HttpStatusCode.OK) {
                return GatewayResponse.createError(GenericError("Problem beim Laden der Transaktionen"), 500, "")
            }

            val transactions = response.body<Set<TransactionDto>>()

            return GatewayResponse.createSuccess(
                transactions,
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