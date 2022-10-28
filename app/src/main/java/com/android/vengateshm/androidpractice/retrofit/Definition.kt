package com.android.vengateshm.androidpractice.retrofit


import com.google.gson.annotations.SerializedName

data class Definition(
    @SerializedName("definition")
    val definition: String,
    @SerializedName("example")
    val example: String,
    @SerializedName("synonyms")
    val synonyms: List<String>,
)