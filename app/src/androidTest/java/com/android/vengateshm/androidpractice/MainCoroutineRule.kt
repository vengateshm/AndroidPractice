package com.android.vengateshm.androidpractice

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MainCoroutineRule(val tesCoroutineDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()) :
    TestWatcher() {
    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(tesCoroutineDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
        tesCoroutineDispatcher.cleanupTestCoroutines()
    }
}