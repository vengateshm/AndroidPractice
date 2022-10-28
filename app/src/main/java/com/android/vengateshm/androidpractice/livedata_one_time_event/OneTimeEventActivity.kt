package com.android.vengateshm.androidpractice.livedata_one_time_event

import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.vengateshm.androidpractice.R
import com.android.vengateshm.androidpractice.launchActivity
import com.android.vengateshm.androidpractice.webview_react_js.WebViewReactJsActivity

class OneTimeEventActivity : AppCompatActivity() {

    private lateinit var viewModel: OneTimeEventViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_time_event)

        viewModel = ViewModelProvider(this)[OneTimeEventViewModel::class.java]

        viewModel.ldOneTimeEvent.observe(this) {
            Toast.makeText(this, "Toast", LENGTH_SHORT).show()
            launchActivity<WebViewReactJsActivity>()
        }

        viewModel.sendEvent()
    }
}