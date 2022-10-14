package com.android.vengateshm.androidpractice.onesdk_webview.utils

import android.app.ActivityOptions
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.vengateshm.androidpractice.R

object ActivityUtil {

    fun openActivity(context: Context, @Animation.Activity animation: Int): Bundle? {
        return when (animation) {
            Animation.Activity.SLIDE_LEFT ->
                ActivityOptions.makeCustomAnimation(context,
                    R.anim.slide_left_enter,
                    R.anim.no_animation).toBundle()
            Animation.Activity.SLIDE_UP ->
                ActivityOptions.makeCustomAnimation(context,
                    R.anim.slide_up_enter,
                    R.anim.no_animation).toBundle()
            else -> null
        }
    }

    fun closeActivity(context: Context, @Animation.Activity animation: Int) {
        when (animation) {
            Animation.Activity.SLIDE_RIGHT ->
                (context as AppCompatActivity)
                    .overridePendingTransition(
                        0, R.anim.slide_out_right
                    )
            Animation.Activity.SLIDE_DOWN ->
                (context as AppCompatActivity)
                    .overridePendingTransition(
                        0, R.anim.slide_down_exit
                    )
            else -> {}
        }
    }
}