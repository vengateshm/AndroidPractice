package com.android.vengateshm.androidpractice

import com.android.vengateshm.androidpractice.testing.NumberCruncher
import com.android.vengateshm.androidpractice.testing.NumberCruncher1
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NumberCruncherTest {
    private val numberCruncher = NumberCruncher()

    @Test
    fun `test getResult`() = runTest {
        val result = numberCruncher.getResult()
        assertEquals(0, result)
    }

    @Test
    fun test() = runTest {
        val numberCruncher = NumberCruncher1(this)
        numberCruncher.calculate()
        assertEquals(0, numberCruncher.results().first())
    }

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Test
    fun test1() = runTest {
        val numberCruncher = NumberCruncher1(this, coroutineTestRule.testDispatcherProvider)
        numberCruncher.calculate()
        assertEquals(0, numberCruncher.results().first())
    }
}