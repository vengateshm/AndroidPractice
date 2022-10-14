package com.android.vengateshm.androidpractice.onesdk_webview.ui

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.core.view.isInvisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.android.vengateshm.androidpractice.R
import com.android.vengateshm.androidpractice.databinding.WebFragmentLayoutBinding
import com.android.vengateshm.androidpractice.onesdk_webview.*
import com.android.vengateshm.androidpractice.onesdk_webview.manager.OneSDKWebMFE
import com.android.vengateshm.androidpractice.onesdk_webview.manager.OneSDKWebMFEConfig
import com.android.vengateshm.androidpractice.onesdk_webview.manager.OneSDKWebMFEWatcher
import com.google.gson.Gson
import com.google.gson.JsonObject

class OneSDKWebFragment : Fragment() {
    companion object {
        const val FRAGMENT_NAME = "OneSDKWebFragment"
        const val URL_TO_LOAD = "URL_TO_LOAD"
        const val URL_PARAM = "URL_PARAM"
        const val SERVICE_ID = "SERVICE_ID"
        const val IS_ERROR = "IS_ERROR"
        const val JS_INTERFACE = "JS_INTERFACE"

        val INTERVAL = getIntervalBasedOnCountry()

        private fun getIntervalBasedOnCountry(): Int {
            return if (OneSDKWebMFE.config.country == COUNTRY_HK)
                1000 * 60 * 9
            else 1000 * 60 * 4
        }

        fun getInstance(bundle: Bundle?): OneSDKWebFragment {
            val f = OneSDKWebFragment()
            bundle?.let {
                f.arguments = bundle
            }
            return f
        }
    }

    private var _binding: WebFragmentLayoutBinding? = null
    val binding
        get() = _binding!!
    private var jsInterface: Any? = null

    private var webServiceCallMadeInWebView: Boolean = false
    private var refreshScreen: Boolean = false
    private var isErrorShown: Boolean = false
    private var jsonString: String = ""
    private var loadUrl: String = ""

    val extendSessionHandler: Handler = Handler(Looper.getMainLooper())
    val extendSessionRunnable: Runnable = Runnable {
        triggerExtendSessionIfRequired()
        startTimer()
    }

    fun startTimer() {
        extendSessionHandler.postDelayed(extendSessionRunnable, INTERVAL.toLong())
    }

    fun stopTimer() {
        extendSessionHandler.removeCallbacks(extendSessionRunnable)
    }

    private fun triggerExtendSessionIfRequired() {
        if (webServiceCallMadeInWebView) {
            webServiceCallMadeInWebView = false
            val bundle = Bundle()
                .apply {
                    putBoolean(EXTEND_SESSION, true)
                }
            OneSDKWebMFE.oneSDKWebMFEWatcher.onMfeStatsListener(OneSDKWebMFEWatcher.Status.COMPLETED,
                bundle)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        if (_binding == null) {
            _binding =
                DataBindingUtil.inflate(inflater, R.layout.web_fragment_layout, container, false)
        }
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        jsInterface = arguments?.getParcelable(JS_INTERFACE)
        if (jsInterface == null) {
            jsInterface = JsInterface()
        }
        with(binding.webviewFrame) {
            addJavascriptInterface(jsInterface!!, "android")
            webViewClient = webViewClient()
            settings.javaScriptEnabled = true
            if (OneSDKWebMFE.config.environment != OneSDKWebMFEConfig.Environment.PROD) {
                WebView.setWebContentsDebuggingEnabled(true)
                settings.domStorageEnabled = true
            }

            loadUrl = arguments?.getString(URL_TO_LOAD) ?: ""
            jsonString = arguments?.getString(URL_PARAM) ?: ""
            if (loadUrl.isNotBlank()) {
                loadUrl(loadUrl)
            }
        }
    }

    fun webViewClient(): WebViewClient {
        return object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?,
            ) {
                super.onReceivedError(view, request, error)
                showError(request?.url.toString())
            }

            override fun onReceivedHttpError(
                view: WebView?,
                request: WebResourceRequest?,
                errorResponse: WebResourceResponse?,
            ) {
                super.onReceivedHttpError(view, request, errorResponse)
                showError(request?.url.toString())
            }

            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?,
            ) {
                super.onReceivedSslError(view, handler, error)
                showError(error?.url)
            }

            private fun showError(url: String?) {
                if (isErrorShown.not() && url == loadUrl) {
                    binding.webviewFrame.isInvisible = true
                    val bundle = Bundle()
                        .apply {
                            putBoolean(IS_ERROR, true)
                            putString(SERVICE_ID, arguments?.getString(SERVICE_ID) ?: "")
                        }
                    OneSDKWebMFE.oneSDKWebMFEWatcher.onMfeStatsListener(OneSDKWebMFEWatcher.Status.EXIT,
                        bundle)
                    isErrorShown = true
                }
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                view?.scrollTo(0, 0)
                super.onPageFinished(view, url)
                if (url == loadUrl) {
                    with(binding.webviewFrame) {
                        evaluateJavascript(jsonString, null)
                        clearHistory()
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        refresh()
        startTimer()
    }

    fun refresh() {
        if (refreshScreen) {
            refreshScreen = false
            binding.webviewFrame.loadUrl(loadUrl)
        }
    }

    override fun onPause() {
        super.onPause()
        stopTimer()
        triggerExtendSessionIfRequired()
    }

    inner class JsInterface {
        @JavascriptInterface
        fun closeScreen(data: String) {
            activity?.finish()
        }

        @JavascriptInterface
        fun navigateTo(data: String) {
            val gson = Gson()
            val dataObj = gson.fromJson(data, JsonObject::class.java)
            val action = dataObj.get("action").asString
            if (NAVIGATE_FOR_MDA_LANDING == action) {
                refreshScreen = true
            } else if (LOGOUT_SURVEY == action) {
                webServiceCallMadeInWebView = true
            }
            val bundle = Bundle()
                .apply {
                    putString(KEY_DATA, data)
                }
            OneSDKWebMFE.oneSDKWebMFEWatcher.onMfeStatsListener(OneSDKWebMFEWatcher.Status.COMPLETED,
                bundle)
        }
    }
}