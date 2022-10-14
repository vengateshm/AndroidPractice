package com.android.vengateshm.androidpractice.onesdk_webview.manager

import android.os.Bundle

interface OneSDKWebMFEWatcher {
    fun onMfeStatsListener(var1: Int, var2: Bundle)

    @Retention(AnnotationRetention.SOURCE)
    annotation class Status {
        companion object {
            var ENTER = 0
            var EXIT = 1
            var COMPLETED = 2
        }
    }
}