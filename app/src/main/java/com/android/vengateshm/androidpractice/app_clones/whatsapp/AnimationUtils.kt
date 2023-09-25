package com.android.vengateshm.androidpractice.app_clones.whatsapp

import android.animation.ValueAnimator
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.view.isVisible
import com.google.android.material.floatingactionbutton.FloatingActionButton

fun animateFabByViewProperty(show: Boolean, fab: FloatingActionButton) {
    val startY = if (show) fab.height else 0
    val endY = if (show) 0 else fab.height

    fab.translationY = startY.toFloat()
    fab.isVisible = show

    fab.animate()
        .translationY(endY.toFloat())
        .setInterpolator(AccelerateDecelerateInterpolator())
        .setDuration(250L)
        .start()
}

fun animateFabByValueAnimator(show: Boolean, fab: FloatingActionButton) {
    val startY = if (show) fab.height else 0
    val endY = if (show) 0 else fab.height

    ValueAnimator.ofFloat(startY.toFloat(), endY.toFloat())
        .apply {
            interpolator = AccelerateDecelerateInterpolator()
            duration = 1000L
            addUpdateListener { animator ->
                val animVal = animator.animatedValue as Float
                fab.translationY = animVal
                if (!show && animVal == 0f) {
                    fab.isVisible = false
                } else if (show && animVal.toInt() == fab.height) {
                    fab.isVisible = true
                }
            }
        }.start()
}