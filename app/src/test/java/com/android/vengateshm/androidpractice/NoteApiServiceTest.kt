package com.android.vengateshm.androidpractice

import com.android.vengateshm.androidpractice.http_client.ktor_client.KtorNote
import com.android.vengateshm.androidpractice.http_client.ktor_client.NoteApiService
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.fullPath
import io.ktor.http.headersOf
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.FileNotFoundException
import java.net.URL

fun String.readFile(): String {
    val content: URL? = ClassLoader.getSystemResource(this)
    return content?.readText() ?: throw FileNotFoundException("file path: $this was not found")
}

class NoteApiServiceTest {

    private val addNoteFile = "ktor-client/AddNoteResponse.json".readFile()
    private val allNotesFile = "ktor-client/AllNotesResponse.json".readFile()
    private val deleteNoteFile = "ktor-client/DeleteNote.json".readFile()
    private val mockEngine = MockEngine { request ->
        when (request.url.fullPath) {
            "/note/add" -> {
                respond(
                    content = addNoteFile,
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }

            "/note/all" -> {
                respond(
                    content = allNotesFile,
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }

            "/note/delete/100" -> {
                respond(
                    content = deleteNoteFile,
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }

            else -> error("Error ${request.url.fullPath}")
        }
    }
    private lateinit var noteApiService: NoteApiService

    @Before
    fun setup() {
        val httpClient = HttpClient() {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                    }
                )
            }
        }
        noteApiService = NoteApiService(httpClient)
    }

    @Test
    fun addNote_Assert_IsPathRight() = runTest {
        noteApiService.addNote(
            KtorNote(
                description = "Complete GCP practitioner course",
                priority = "HIGH"
            )
        )
        val requestPath = mockEngine.requestHistory.first().url.fullPath
        Assert.assertEquals("/note/add", requestPath)
    }

//    @Test
//    fun addNote_Assert_IsDescriptionRight() = runTest {
//        val description = noteApiService
//            .addNote(
//                KtorNote(
//                    description = "Complete GCP practitioner course",
//                    priority = "HIGH"
//                )
//            ).description
//        Assert.assertEquals("Complete GCP practitioner course", description)
//    }
//
//    @Test
//    fun deleteNote_Assert_IsNoteDeleted() = runTest {
//        val response = noteApiService.deleteNote("100")
//        val status = response.status.isSuccess()
//        Assert.assertEquals(true, status)
//    }

    @After
    fun teardown() {
        mockEngine.close()
    }
}