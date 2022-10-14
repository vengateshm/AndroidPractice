package com.android.vengateshm.androidpractice.onesdk_webview.models

data class CommonAppParams(
    val appVersion: String,
    val deviceOSVersion: String,
    val deviceId: String,
    val locale: String,
    val deviceOSType: String,
    val channel: String,
    val sysGenUid: String,
    val serviceFlag: String,
    val authGWCode: String,
    val region: String,
    val clientSegment: String,
)
