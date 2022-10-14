package com.android.vengateshm.androidpractice.airbnb.mavericks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.vengateshm.androidpractice.R
import com.android.vengateshm.androidpractice.airbnb.mavericks.feature.UserFragment

class MavericksSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mavericks_sample)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, UserFragment())
            .commit()
    }
}