package com.android.vengateshm.androidpractice.onesdk_webview.manager

import androidx.annotation.StringDef
import com.android.vengateshm.androidpractice.onesdk_webview.*
import com.android.vengateshm.androidpractice.onesdk_webview.models.CommonAppParams

class OneSDKWebMFEConfig {
    @Environment
    lateinit var environment: String

    @Country
    lateinit var country: String

    lateinit var commonAppParams: CommonAppParams

    @Retention(AnnotationRetention.SOURCE)
    @StringDef(value = [Environment.SIT, Environment.UAT, Environment.PILOT, Environment.PROD])
    annotation class Environment {
        companion object {
            const val SIT: String = ENV_SIT
            const val UAT: String = ENV_UAT
            const val PILOT: String = ENV_PILOT
            const val PROD: String = ENV_PROD
        }
    }

    @Retention(AnnotationRetention.SOURCE)
    @StringDef(value = [Country.SG, Country.HK, Country.TW])
    annotation class Country {
        companion object {
            const val SG: String = COUNTRY_SG
            const val HK: String = COUNTRY_HK
            const val TW: String = COUNTRY_TW
        }
    }
}