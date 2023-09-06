package com.android.vengateshm.androidpractice.viewpager2_transformations

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class ZoomOutPageTransformer : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val scaleFactor = 0.85f.coerceAtLeast(1 - abs(position))
        val alpha = 1 - abs(position)
        page.scaleX = scaleFactor
        page.scaleY = scaleFactor
        page.alpha = alpha
        page.translationX = -page.width * position
    }
}