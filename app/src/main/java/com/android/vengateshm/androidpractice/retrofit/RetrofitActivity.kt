package com.android.vengateshm.androidpractice.retrofit

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.android.vengateshm.androidpractice.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RetrofitActivity : AppCompatActivity() {

    private lateinit var tvText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        tvText = findViewById(R.id.tvText)

        lifecycleScope.launch(Dispatchers.IO) {
            val anxietyResponse = async {
                Log.d("RETROFIT", "Anxiety")
                val response = DictionaryApiClient.api.getWordInfo("anxiety")
                withContext(Dispatchers.Main.immediate) {
                    tvText.text = response.toString()
                }
            }

            val jeopardyResponse = async {
                Log.d("RETROFIT", "Jeopardy")
                val response = DictionaryApiClient.api.getWordInfo("jeopardy")
                withContext(Dispatchers.Main.immediate) {
                    tvText.text = response.toString()
                }
            }

//            awaitAll(anxietyResponse, jeopardyResponse).forEach {
//                Log.d("RETROFIT", it.toString())
//            }

//            Log.d("RETROFIT", anxietyResponse.toString())
//            Log.d("RETROFIT", jeopardyResponse.toString())
        }
    }
}