package com.android.vengateshm.androidpractice.viewpager2_transformations

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class ScaleInTransformer : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        // Scale the page based on its position
        val scale = 1 - abs(position)
        page.scaleX = scale
        page.scaleY = scale

        // Set alpha for fading effect
        page.alpha = scale
    }
}