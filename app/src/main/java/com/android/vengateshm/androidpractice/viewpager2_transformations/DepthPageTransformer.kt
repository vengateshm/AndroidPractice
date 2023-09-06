package com.android.vengateshm.androidpractice.viewpager2_transformations

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class DepthPageTransformer : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        when {
            position < -1 -> {
                page.alpha = 0f
            }

            position <= 0 -> {
                page.alpha = 1f
                page.translationX = 0f
                page.scaleX = 1f
                page.scaleY = 1f
            }

            position <= 1 -> {
                page.alpha = 1 - position
                page.translationX = page.width * -position
                val scaleFactor = 0.75f + (1 - 0.75f) * (1 - abs(position))
                page.scaleX = scaleFactor
                page.scaleY = scaleFactor
            }

            else -> {
                page.alpha = 0f
            }
        }
    }
}