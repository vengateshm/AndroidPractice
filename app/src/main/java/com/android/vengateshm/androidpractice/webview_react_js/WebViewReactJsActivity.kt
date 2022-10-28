package com.android.vengateshm.androidpractice.webview_react_js

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.*
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity
import com.android.vengateshm.androidpractice.databinding.ActivityWebviewReactJsBinding

class WebViewReactJsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebviewReactJsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWebviewReactJsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        WebView.setWebContentsDebuggingEnabled(true)

        with(binding.webview) {
            addJavascriptInterface(MyJSInterface(context), "android")
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            webViewClient = MyWebViewClient(binding)
            webChromeClient = WebChromeClient()
            //loadUrl("https://vengateshm.github.io/react_js_recipe_app/")
            loadUrl("file:///android_asset/sample.html")
        }
    }

    class MyJSInterface(private val context: Context) {

        @JavascriptInterface
        public fun makeToast(message: String) {
            Toast.makeText(context, message, LENGTH_LONG).show()
        }
    }

    class MyWebViewClient(private val binding: ActivityWebviewReactJsBinding) : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
        }

        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?,
        ) {
            super.onReceivedError(view, request, error)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            val message = "From Android"
            /*binding.webview.evaluateJavascript(
                "window.showAlertMessage(\"hjhjhj\")", null
            )*/
            binding.webview.evaluateJavascript(
                "window:greet(\"Rockstar\")", null
            )
        }
    }
}