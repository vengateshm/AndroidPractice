package com.android.vengateshm.androidpractice.app_clones.whatsapp

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class BorderedCircle(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val borderWidth = 10f

    init {
        circlePaint.color = Color.parseColor("#999999")
    }
}