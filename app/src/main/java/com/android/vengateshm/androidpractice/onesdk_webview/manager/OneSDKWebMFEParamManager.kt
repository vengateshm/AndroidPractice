package com.android.vengateshm.androidpractice.onesdk_webview.manager

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import com.android.vengateshm.androidpractice.onesdk_webview.models.DeviceInfoModel
import com.android.vengateshm.androidpractice.onesdk_webview.models.DigiInfoModel
import com.android.vengateshm.androidpractice.onesdk_webview.models.LandingConfigModel
import com.android.vengateshm.androidpractice.onesdk_webview.ui.OneSDKWebFragment
import com.android.vengateshm.androidpractice.onesdk_webview.ui.OneSDKWebPageLandingActivity
import com.android.vengateshm.androidpractice.onesdk_webview.utils.ActivityUtil
import com.android.vengateshm.androidpractice.onesdk_webview.utils.Animation
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken

class OneSDKWebMFEParamManager {

    init {
        SERVICE_JSON = loadJSONFromAsset(OneSDKWebMFE.applicationContext, "WebConfig_SG.json")
        val jsonObject = JsonParser().parse(SERVICE_JSON).asJsonObject
        val jsonArray = jsonObject.getAsJsonArray(SERVICES)
        jsonArray.forEach {
            serviceObject[it.asJsonObject.get(SERVICE_ID).asString] = it.asJsonObject
        }
    }

    companion object {
        val TAG = OneSDKWebMFEParamManager::class.java.name

        val CHANNEL = "channel"
        val SYS_GEN_UID = "sysGenUid"
        val SERVICE_FLAG = "serviceFlag"
        val ENCRYPTED_PORTFOLIOS = "encryptedPortfolios"
        val AUTH_GW_CODE = "authGWCode"
        val REGION = "region"
        val CLIENT_SEGMENT = "clientSegment"
        val LANDING_PAGE = "landingPage"
        val PORTFOLIO_NO = "portfolioNo"
        val DEVICE_INFO = "deviceInfo"
        val ENTRY_POINT = "entryPoint"
        val HOLDING_ID = "holdingId"
        val PARAMS = "params"
        val RENDERING_STRING = "renderingString"
        val SERVICE_URL = "serviceURLs"
        val CHANNEL_MB = "MB"
        val SERVICE_ID = "serviceId"

        var SERVICE_JSON = ""
        var serviceObject: HashMap<String, JsonObject> = hashMapOf()

        val SERVICES = "services"

        fun launchWebMFEPage(
            context: Context,
            digiInfoModel: DigiInfoModel,
            landingConfigModel: LandingConfigModel,
            serviceUrl: String?,
            jsInterface: Parcelable?,
        ): Boolean {
            val serviceJsonObject = serviceObject[landingConfigModel.serviceId]
            if (serviceJsonObject != null) {
                val converter = Gson()
                val type = object : TypeToken<List<String>>() {}.type
                val paramList =
                    converter.fromJson<List<String>>(serviceJsonObject.get(PARAMS).asJsonArray,
                        type)
                if (paramList != null && paramList.isNotEmpty()) {
                    var finalServiceUrl = ""
                    if (serviceUrl == null || serviceUrl.isEmpty()) {
                        val jsonUrls = serviceJsonObject.get(SERVICE_URL).asJsonObject
                        finalServiceUrl = when (OneSDKWebMFE.config.environment) {
                            OneSDKWebMFEConfig.Environment.SIT -> {
                                jsonUrls.get("sit").asString
                            }
                            OneSDKWebMFEConfig.Environment.UAT -> {
                                jsonUrls.get("uat").asString
                            }
                            OneSDKWebMFEConfig.Environment.PILOT -> {
                                jsonUrls.get("pilot").asString
                            }
                            else -> {
                                jsonUrls.get("prod").asString
                            }
                        }
                    } else {
                        finalServiceUrl = serviceUrl
                    }
                    launchOneSDKWebMfeActivity(
                        context,
                        finalServiceUrl,
                        getServiceParams(
                            digiInfoModel,
                            landingConfigModel,
                            paramList,
                            serviceJsonObject.get(RENDERING_STRING).asString),
                        landingConfigModel.serviceId,
                        jsInterface)
                    return true
                } else {
                    return false
                }
            } else {
                return false
            }
        }

        fun launchWebMFEPage(
            context: Context,
            serviceId: String,
            digiParamList: HashMap<String, String>,
            serviceUrl: String?,
            jsInterface: Parcelable?,
        ): Boolean {
            val serviceJsonObject = serviceObject[serviceId]
            if (serviceJsonObject != null) {
                val converter = Gson()
                val type = object : TypeToken<List<String>>() {}.type
                val paramList =
                    converter.fromJson<List<String>>(serviceJsonObject.get(PARAMS).asJsonArray,
                        type)
                return if (paramList != null && paramList.isNotEmpty()) {
                    val finalServiceUrl = getFinalServiceUrl(serviceUrl, serviceJsonObject)
                    launchOneSDKWebMfeActivity(
                        context,
                        finalServiceUrl,
                        getServiceParams(
                            paramList,
                            digiParamList,
                            serviceJsonObject.get(RENDERING_STRING).asString),
                        serviceId,
                        jsInterface)
                    true
                } else {
                    false
                }
            } else {
                return false
            }
        }

        fun getFinalServiceUrl(serviceUrl: String?, serviceJsonObject: JsonObject): String {
            var finalServiceUrl = ""
            if (serviceUrl == null || serviceUrl.isEmpty()) {
                val jsonUrls = serviceJsonObject.get(SERVICE_URL).asJsonObject
                finalServiceUrl = when (OneSDKWebMFE.config.environment) {
                    OneSDKWebMFEConfig.Environment.SIT -> {
                        jsonUrls.get("sit").asString
                    }
                    OneSDKWebMFEConfig.Environment.UAT -> {
                        jsonUrls.get("uat").asString
                    }
                    OneSDKWebMFEConfig.Environment.PILOT -> {
                        jsonUrls.get("pilot").asString
                    }
                    else -> {
                        jsonUrls.get("prod").asString
                    }
                }
            } else {
                finalServiceUrl = serviceUrl
            }
            return finalServiceUrl
        }

        fun getServiceParams(
            digiInfoModel: DigiInfoModel,
            landingConfigModel: LandingConfigModel,
            paramList: List<String>,
            renderingString: String,
        ): String {
            val jsonObject = JsonObject()
            val gson = Gson()

            val appParams = OneSDKWebMFE.config.commonAppParams
            val deviceInfo = DeviceInfoModel(
                appParams.appVersion,
                appParams.deviceOSVersion,
                appParams.deviceId,
                appParams.locale,
                appParams.deviceOSType,
            )

            if (paramList.contains(CHANNEL)) {
                jsonObject.addProperty(CHANNEL, CHANNEL_MB)
            }
            if (paramList.contains(SYS_GEN_UID)) {
                jsonObject.addProperty(SYS_GEN_UID, appParams.sysGenUid)
            }
            if (paramList.contains(SERVICE_FLAG)) {
                jsonObject.addProperty(SERVICE_FLAG, landingConfigModel.serviceId)
            }
            if (paramList.contains(ENCRYPTED_PORTFOLIOS)) {
                val portfoliosJson = gson.toJson(digiInfoModel.encryptedPortfoliosModel)
                jsonObject.add(ENCRYPTED_PORTFOLIOS, (JsonParser().parse(portfoliosJson)))
            }
            if (paramList.contains(AUTH_GW_CODE)) {
                jsonObject.addProperty(AUTH_GW_CODE, appParams.authGWCode)
            }
            if (paramList.contains(REGION)) {
                jsonObject.addProperty(REGION, appParams.region)
            }
            if (paramList.contains(CLIENT_SEGMENT)) {
                jsonObject.addProperty(CLIENT_SEGMENT, appParams.clientSegment)
            }
            if (paramList.contains(LANDING_PAGE)) {
                jsonObject.addProperty(LANDING_PAGE, landingConfigModel.landingPage)
            }
            if (paramList.contains(PORTFOLIO_NO)) {
                jsonObject.addProperty(PORTFOLIO_NO, digiInfoModel.portfolioNo)
            }
            if (paramList.contains(DEVICE_INFO)) {
                val deviceInfoJson = gson.toJson(deviceInfo)
                jsonObject.add(DEVICE_INFO, JsonParser().parse(deviceInfoJson))
            }
            if (paramList.contains(ENTRY_POINT)) {
                jsonObject.addProperty(ENTRY_POINT, landingConfigModel.entryPoint)
            }
            if (paramList.contains(HOLDING_ID)) {
                jsonObject.addProperty(HOLDING_ID, digiInfoModel.holdingId)
            }
            return "$renderingString(${gson.toJson(jsonObject)})"
        }

        fun getServiceParams(
            jsonConfigParamList: List<String>,
            digiParamList: HashMap<String, String>,
            renderingString: String,
        ): String {
            val jsonObject = JsonObject()
            val gson = Gson()

            jsonConfigParamList.forEach {
                if (digiParamList.containsKey(it)) {
                    jsonObject.addProperty(it, digiParamList[it])
                }
            }

            return "$renderingString(${gson.toJson(jsonObject)})"
        }

        fun loadJSONFromAsset(context: Context, filePath: String): String {
            return try {
                val ips = context.assets.open(filePath)
                val size = ips.available()
                val buffer = ByteArray(size = size)
                val read = ips.read(buffer)
                ips.close()
                String(buffer, Charsets.UTF_8)
            } catch (e: Exception) {
                ""
            }
        }

        fun launchOneSDKWebMfeActivity(
            context: Context,
            urlToLoad: String,
            urlParam: String,
            serviceId: String,
            jsInterface: Parcelable?,
        ) {
            val intent = Intent(context, OneSDKWebPageLandingActivity::class.java)
            intent.putExtra(OneSDKWebFragment.URL_TO_LOAD, urlToLoad)
            intent.putExtra(OneSDKWebFragment.URL_PARAM, urlParam)
            intent.putExtra(OneSDKWebFragment.SERVICE_ID, serviceId)
            intent.putExtra(OneSDKWebFragment.JS_INTERFACE, jsInterface)
            context.startActivity(intent,
                ActivityUtil.openActivity(context, Animation.Activity.SLIDE_LEFT))
        }
    }
}