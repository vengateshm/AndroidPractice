package com.android.vengateshm.androidpractice.retrofit


import com.google.gson.annotations.SerializedName

data class Meaning(
    @SerializedName("definitions")
    val definitions: List<Definition>,
    @SerializedName("partOfSpeech")
    val partOfSpeech: String,
)