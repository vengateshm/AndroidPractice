package com.android.vengateshm.androidpractice.onesdk_webview.manager

import com.android.vengateshm.androidpractice.onesdk_webview.models.PortfolioModel

data class EncryptedPortfoliosModel(
    val wealthPortfolios: ArrayList<PortfolioModel>,
    val relatedPortfolios: ArrayList<PortfolioModel>,
)
