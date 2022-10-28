package com.android.vengateshm.androidpractice.onesdk_webview.manager

import android.content.Context
import android.os.Parcelable
import com.android.vengateshm.androidpractice.onesdk_webview.models.DigiInfoModel
import com.android.vengateshm.androidpractice.onesdk_webview.models.LandingConfigModel

object OneSDKWebMFE {
    lateinit var applicationContext: Context
    lateinit var config: OneSDKWebMFEConfig
    lateinit var oneSDKWebMFEWatcher: OneSDKWebMFEWatcher

    fun init(
        applicationContext: Context,
        config: OneSDKWebMFEConfig,
        watcher: OneSDKWebMFEWatcher,
    ): Unit {
        this.applicationContext = applicationContext
        this.config = config
        this.oneSDKWebMFEWatcher = watcher
    }

    fun launchWebMFEPage(
        context: Context,
        digiInfoModel: DigiInfoModel,
        landingConfigModel: LandingConfigModel,
    ) {
        OneSDKWebMFEParamManager.launchWebMFEPage(
            context,
            digiInfoModel,
            landingConfigModel,
            null,
            null
        )
    }

    fun launchWebMFEPage(
        context: Context,
        digiInfoModel: DigiInfoModel,
        landingConfigModel: LandingConfigModel,
        serviceUrl: String,
    ) {
        OneSDKWebMFEParamManager.launchWebMFEPage(
            context,
            digiInfoModel,
            landingConfigModel,
            serviceUrl,
            null
        )
    }

    fun launchWebMFEPage(
        context: Context,
        digiInfoModel: DigiInfoModel,
        landingConfigModel: LandingConfigModel,
        serviceUrl: String,
        jsInterface: Parcelable,
    ) {
        OneSDKWebMFEParamManager.launchWebMFEPage(
            context,
            digiInfoModel,
            landingConfigModel,
            serviceUrl,
            jsInterface
        )
    }

    //
    fun launchWebMFEPage(
        context: Context,
        serviceId: String,
        digiParamList: HashMap<String, String>,
        serviceUrl: String,
    ) {
        OneSDKWebMFEParamManager.launchWebMFEPage(
            context,
            serviceId,
            digiParamList,
            serviceUrl,
            null
        )
    }

    fun launchWebMFEPage(
        context: Context,
        serviceId: String,
        digiParamList: HashMap<String, String>,
        serviceUrl: String,
        jsInterface: Parcelable,
    ) {
        OneSDKWebMFEParamManager.launchWebMFEPage(
            context,
            serviceId,
            digiParamList,
            serviceUrl,
            jsInterface
        )
    }
}