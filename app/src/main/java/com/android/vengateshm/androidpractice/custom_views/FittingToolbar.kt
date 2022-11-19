package com.android.vengateshm.androidpractice.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import java.lang.Integer.max

class FittingToolbar(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var contentWidth = 0
        var contentHeight = 0
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.measure(
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
            )
            contentWidth += child.measuredWidth // 10dp + 20dp
            contentHeight += max(contentHeight,
                child.measuredHeight) // Depends based on the layout positioning
        }

        val widthSize = resolveSizeSimplified(contentWidth, widthMeasureSpec)
        val heightSize = resolveSizeSimplified(contentHeight, heightMeasureSpec)
        setMeasuredDimension(widthSize, heightSize)
    }

    private fun resolveSizeSimplified(contentSize: Int, measureSpec: Int): Int {
        val mode = MeasureSpec.getMode(measureSpec)
        val size = MeasureSpec.getSize(measureSpec)

        return when (mode) {
            MeasureSpec.EXACTLY -> size
            MeasureSpec.AT_MOST -> if (contentSize < size) contentSize else size
            else -> contentSize
        }
    }

    override fun onLayout(p0: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val maxWidth = measuredWidth
        var takenWidth = 0
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (takenWidth + child.measuredWidth < maxWidth) {
                child.layout(takenWidth, 0, takenWidth + child.measuredWidth, height)
                takenWidth += child.measuredWidth
            } else {
                child.layout(0, 0, 0, 0)
            }
        }
    }
}