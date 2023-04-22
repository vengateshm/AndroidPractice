package com.android.vengateshm.androidpractice.testing

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class NumberCruncher1(
    private val coroutineScope: CoroutineScope,
    private val dispatcher: DispatcherProvider = DefaultDispatcherProvider(),
) {
    private val _results = MutableSharedFlow<Int>(replay = 0)
    fun results() = _results.asSharedFlow()

    fun calculate() {
        coroutineScope.launch(dispatcher.default()) {
            val result = NumberCruncher.longRunningOperation()
            delay(20_000)
            _results.emit(result)
        }
    }
}