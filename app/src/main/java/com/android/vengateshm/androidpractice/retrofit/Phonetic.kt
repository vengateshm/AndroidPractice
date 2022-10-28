package com.android.vengateshm.androidpractice.retrofit


import com.google.gson.annotations.SerializedName

data class Phonetic(
    @SerializedName("audio")
    val audio: String,
    @SerializedName("text")
    val text: String,
)