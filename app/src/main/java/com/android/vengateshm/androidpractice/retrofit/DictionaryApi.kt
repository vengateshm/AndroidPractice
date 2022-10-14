package com.android.vengateshm.androidpractice.retrofit

import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {
    @GET("v2/entries/en/{word}")
    suspend fun getWordInfo(@Path("word") word: String): WordInfoResponse

    companion object {
        const val BASE_URL = "https://api.dictionaryapi.dev/api/"
    }
}