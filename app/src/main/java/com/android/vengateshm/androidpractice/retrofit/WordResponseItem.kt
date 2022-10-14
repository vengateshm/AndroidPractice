package com.android.vengateshm.androidpractice.retrofit


import com.google.gson.annotations.SerializedName

data class WordResponseItem(
    @SerializedName("meanings")
    val meanings: List<Meaning>,
    @SerializedName("phonetics")
    val phonetics: List<Phonetic>,
    @SerializedName("word")
    val word: String
)