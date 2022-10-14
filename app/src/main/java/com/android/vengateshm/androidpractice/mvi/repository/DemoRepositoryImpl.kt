package com.android.vengateshm.androidpractice.mvi.repository

import android.content.Context

class DemoRepositoryImpl(private val context: Context) : DemoRepository {

    companion object {
        const val WEB_CONFIG_FILES_NAME = "WebConfig"
    }

    override fun countries(): List<String> {
        return context.assets.list("")
            ?.filter { it -> it.contains(WEB_CONFIG_FILES_NAME) } ?: emptyList()
    }
}