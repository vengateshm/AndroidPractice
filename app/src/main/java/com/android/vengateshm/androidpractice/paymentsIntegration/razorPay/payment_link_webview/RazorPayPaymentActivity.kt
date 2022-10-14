package com.android.vengateshm.androidpractice.paymentsIntegration.razorPay.payment_link_webview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.android.vengateshm.androidpractice.R


class RazorPayPaymentActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_razor_pay_payment)

        webView = findViewById(R.id.webView)

//        loadUrlInWebView("https://rzp.io/i/d0aaXTbE53")
        loadUrlInWebView("https://rzp.io/i/i9WEqlJ")
//        openBrowser("https://rzp.io/i/L15Y6F71K5")
    }

    private fun loadUrlInWebView(url: String) {
        with(webView) {
            settings.javaScriptEnabled = true
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
//                    if (url?.startsWith("http://android-vengateshm-androidpractice.com/redirect/category")!!) {
//                        finish()
//                        return false
//                    }
                    return super.shouldOverrideUrlLoading(view, url)
                }

                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?,
                ): Boolean {
                    if (request?.url?.toString()
                            ?.startsWith("https://api.razorpay.com/v1/payments/")!!
                        &&
                        request.url?.toString()
                            ?.contains("callback")!!
                    ) {
                        finish()
                        return false
                    }
                    return super.shouldOverrideUrlLoading(view, request)
                }

                /*override fun shouldInterceptRequest(
                    view: WebView?,
                    url: String?,
                ): WebResourceResponse? {
                    return super.shouldInterceptRequest(view, url)
                }

                override fun shouldInterceptRequest(
                    view: WebView?,
                    request: WebResourceRequest?,
                ): WebResourceResponse? {
                    if (request?.url?.toString()
                            ?.startsWith("http://android-vengateshm-androidpractice.com/redirect/category")!!
                    ) {
                        finish()
                        return null
                    }
                    return super.shouldInterceptRequest(view, request)
                }*/
            }
            loadUrl(url)
        }
    }

    private fun openBrowser(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) webView.goBack() // if there is previous page open it
        else super.onBackPressed() //if there is no previous page, close app
    }
}