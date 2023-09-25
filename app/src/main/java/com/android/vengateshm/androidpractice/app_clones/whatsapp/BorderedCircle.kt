package com.android.vengateshm.androidpractice.app_clones.whatsapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

class BorderedCircle(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val segmentPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val borderWidth = 10f
    private var numSegments = listOf<BorderedData>()
    private val totalAngle = 360f
    private var segmentAngle = 0f

    init {
        circlePaint.color = Color.parseColor("#999999")
        segmentPaint.color = Color.parseColor("#DF00FF")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = (min(width, height) - borderWidth) / 2f

        if (numSegments.isNotEmpty() && segmentAngle > 0f) {
            // Draw the segments
            val rectF =
                RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
            for (i in numSegments.indices) {
                segmentPaint.color =
                    if (numSegments[i].highlight) Color.parseColor("#DF00FF") else Color.parseColor(
                        "#999999"
                    )
                //canvas?.save()
                //canvas?.rotate(90f)
                canvas?.drawArc(rectF, 90+i * segmentAngle, (segmentAngle - 4), false, segmentPaint)
                //canvas?.restore()
            }
        }
    }

    fun setSegments(segments: List<BorderedData>) {
        if (segments.isEmpty()) return
        numSegments = segments
        segmentAngle = totalAngle / numSegments.size.toFloat()

        invalidate()
    }
}

data class BorderedData(
    val highlight: Boolean,
)