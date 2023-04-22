package com.android.vengateshm.androidpractice.testing

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NumberCruncher {
    suspend fun getResult(): Int {
        return withContext(Dispatchers.Default) {
            longRunningOperation()
        }
    }

    companion object {
        fun longRunningOperation(): Int {
            val list = mutableListOf<Int>()

            for (i in 0..1_000) {
                list.add(i)
            }
            for (i in 0..20_000) {
                list.shuffle()
                list.sort()
            }

            return list.first()
        }
    }
}