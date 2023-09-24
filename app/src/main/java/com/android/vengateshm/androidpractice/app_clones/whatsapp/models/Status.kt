package com.android.vengateshm.androidpractice.app_clones.whatsapp.models

data class Status(
    val id: Int,
    val userName: String,
    val statusContents: List<StatusContent>,
)
