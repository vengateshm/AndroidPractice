package com.android.vengateshm.androidpractice.viewpager2_transformations

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class RotationPageTransformer : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val rotation = 180 * position
        page.rotation = rotation
        page.pivotX = page.width * 0.5f
        page.pivotY = page.height * 0.5f
    }
}