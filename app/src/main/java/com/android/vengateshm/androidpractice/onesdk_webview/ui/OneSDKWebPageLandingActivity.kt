package com.android.vengateshm.androidpractice.onesdk_webview.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.android.vengateshm.androidpractice.R
import com.android.vengateshm.androidpractice.databinding.WebpageActivityLandingBinding
import com.android.vengateshm.androidpractice.onesdk_webview.ACTION_CLOSE_PAGE
import com.android.vengateshm.androidpractice.onesdk_webview.WEBMFE_ACTION_FINISH_ACTIVITY
import com.android.vengateshm.androidpractice.onesdk_webview.manager.OneSDKWebMFE
import com.android.vengateshm.androidpractice.onesdk_webview.manager.OneSDKWebMFEWatcher
import com.android.vengateshm.androidpractice.onesdk_webview.utils.ActivityUtil
import com.android.vengateshm.androidpractice.onesdk_webview.utils.Animation

class OneSDKWebPageLandingActivity : AppCompatActivity() {

    companion object {
        val ACTIVITY_NAME = "WebPageActivity"
        val USER_INTERACTION_EVENT = "USER_INTERACTION_EVENT"
    }

    private lateinit var binding: WebpageActivityLandingBinding
    private lateinit var serviceName: String

    val CloseWebPageBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.action?.let {
                if (it.equals(ACTION_CLOSE_PAGE, ignoreCase = true)) {
                    finish()
                }
            }
        }
    }

    private lateinit var fragment: OneSDKWebFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewBinding()
        launchFragment()
        serviceName = intent.extras?.getString(OneSDKWebFragment.SERVICE_ID, "") ?: ""
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(CloseWebPageBroadcastReceiver,
                IntentFilter("$ACTION_CLOSE_PAGE$serviceName"))
    }

    private fun viewBinding() {
        this.binding = DataBindingUtil.setContentView(this, R.layout.webpage_activity_landing)
        this.binding.lifecycleOwner = this
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this)
            .unregisterReceiver(CloseWebPageBroadcastReceiver)
    }

    override fun onBackPressed() {
        if (fragment.binding.webviewFrame.canGoBack()) {
            fragment.binding.webviewFrame.goBack()
        } else {
            super.onBackPressed()
            LocalBroadcastManager.getInstance(this).sendBroadcast(Intent(
                WEBMFE_ACTION_FINISH_ACTIVITY))
        }
    }

    private fun launchFragment() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        fragment = OneSDKWebFragment.getInstance(intent?.extras)
        ft.add(binding.container.id, fragment, OneSDKWebFragment.FRAGMENT_NAME)
        ft.commit()
    }

    override fun finish() {
        super.finish()
        ActivityUtil.closeActivity(this, Animation.Activity.SLIDE_RIGHT)
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        callOnUserInteraction()
    }

    private fun callOnUserInteraction() {
        val bundle = Bundle()
        bundle.putString(USER_INTERACTION_EVENT, "on_user_interacted")
        OneSDKWebMFE.oneSDKWebMFEWatcher.onMfeStatsListener(OneSDKWebMFEWatcher.Status.EXIT, bundle)
    }
}