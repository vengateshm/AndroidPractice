package com.android.vengateshm.androidpractice.http_client.ktor_client

import androidx.annotation.Keep
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.path
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class KtorNote(
    val description: String,
    val priority: String,
)

class NoteApiService(private val httpClient: HttpClient) {
    suspend fun addNote(note: KtorNote): KtorNote {
        return httpClient.post {
            contentType(ContentType.Application.Json)
            url { path("note/add") }
            setBody(note)
        }.body()
    }

    suspend fun getAllNotes(): HttpResponse {
        return httpClient.get {
            contentType(ContentType.Application.Json)
            url { path("note/all") }
        }
    }

    suspend fun deleteNote(id: String): HttpResponse {
        return httpClient.delete {
            contentType(ContentType.Application.Json)
            url { path("note/delete/${id}") }
        }
    }
}