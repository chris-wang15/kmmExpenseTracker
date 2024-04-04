package com.tools.records.data.net

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.core.use
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
val httpClient by lazy {
    HttpClient {
        install(ContentNegotiation) {
            json(
                json = Json {
                    ignoreUnknownKeys = true
                    encodeDefaults = false
                    isLenient = false
                    allowStructuredMapKeys = false
                    prettyPrint = false
                    explicitNulls = true
                    prettyPrintIndent = "    "
                    coerceInputValues = false
                    useArrayPolymorphism = false
                    classDiscriminator = "type"
                    allowSpecialFloatingPointValues = false
                    useAlternativeNames = true
                }
            )
        }
    }
}

suspend fun getServerById(movieId: Int, title: String): ServerResponse {
    return httpClient.use {
        it.get("https://jsonmock.hackerrank.com/api/moviesdata/search/?Title=$title") {
            parameter("movie_id", movieId)
        }
            .body()
    }
}

suspend fun getServerByTitle(title: String = "maze"): ServerResponse {
    return httpClient.use {
        it.get("https://jsonmock.hackerrank.com/api/moviesdata/search/?Title=$title")
            .body()
    }
}

@Serializable
data class ServerResponse(
    @SerialName("total")
    val total: Int,
    @SerialName("data")
    val data: List<ServerDetailData>,
)

@Serializable
data class ServerDetailData(
    @SerialName("Title")
    val title: String,
    @SerialName("Year")
    val year: Int,
)