package ch.levelup.kaesseli

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

open class KtorTestRepository {
    private val client = HttpClient()

    suspend fun greeting(): String {
        val response = client.get("https://ktor.io/docs/")
        return response.bodyAsText()
    }
}