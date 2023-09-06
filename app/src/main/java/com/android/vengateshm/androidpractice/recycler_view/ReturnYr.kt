package com.android.vengateshm.androidpractice.recycler_view

enum class ReturnYr(val value: String) {
    ONE("1Yr Returns"),
    THREE("3Yr Returns"),
    FIVE("5Yr Returns")
}

fun getNextReturn(year: ReturnYr): ReturnYr {
    return when (year) {
        ReturnYr.ONE -> ReturnYr.THREE
        ReturnYr.THREE -> ReturnYr.FIVE
        ReturnYr.FIVE -> ReturnYr.ONE
    }
}