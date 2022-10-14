package com.android.vengateshm.androidpractice.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DictionaryApiClient {
    val api: DictionaryApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .client(OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(DictionaryApi::class.java)
    }
}