package com.android.vengateshm.androidpractice.navigation_component.bottom_navigation

import androidx.lifecycle.ViewModel

class DashBoardViewModel : ViewModel() {
    var scrollPosition: Pair<Int, Int> = Pair(0, 0)

    override fun onCleared() {
        super.onCleared()
    }
}