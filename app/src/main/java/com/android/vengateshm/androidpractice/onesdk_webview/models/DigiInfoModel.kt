package com.android.vengateshm.androidpractice.onesdk_webview.models

import com.android.vengateshm.androidpractice.onesdk_webview.manager.EncryptedPortfoliosModel

data class DigiInfoModel(
    val portfolioNo: String,
    val encryptedPortfoliosModel: EncryptedPortfoliosModel,
    val holdingId: String,
)
