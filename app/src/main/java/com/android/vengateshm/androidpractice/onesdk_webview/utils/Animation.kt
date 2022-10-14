package com.android.vengateshm.androidpractice.onesdk_webview.utils

interface Animation {
    @Retention(AnnotationRetention.SOURCE)
    annotation class Activity {
        companion object {
            var SLIDE_LEFT = 3
            var SLIDE_UP = 4
            var SLIDE_RIGHT = 5
            var SLIDE_DOWN = 6
            var NONE = 7
        }
    }

    @Retention(AnnotationRetention.SOURCE)
    annotation class Fragment {
        companion object {
            var SLIDE_LEFT_TO_RIGHT = 0
            var SLIDE_UP_TO_DOWN = 1
            var NONE = 2
        }
    }
}







