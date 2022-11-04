package com.android.vengateshm.androidpractice

import android.app.Application
import com.airbnb.mvrx.Mavericks
import com.android.vengateshm.androidpractice.mvi.di.demo
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@HiltAndroidApp
class AndroidPracticeApp : Application() {
    override fun onCreate() {
        super.onCreate()

        Mavericks.initialize(this)

        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@AndroidPracticeApp)
            modules(demo)
        }
    }
}