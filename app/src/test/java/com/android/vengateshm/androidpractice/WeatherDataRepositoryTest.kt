package com.android.vengateshm.androidpractice

import com.android.vengateshm.androidpractice.common.Result
import com.android.vengateshm.androidpractice.livedata_flow_viewmodel_testing.WeatherDataRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class WeatherDataRepositoryTest {

    private lateinit var weatherDataRepository: WeatherDataRepository

    @Before
    fun setUp() {
        weatherDataRepository = WeatherDataRepository()
    }

    @Test
    fun fetchWeatherDataTest() {
        runBlocking {
            val data = weatherDataRepository.getWeatherByCity("")
            val successResult = data as Result.Success
            Assert.assertTrue(successResult.data in 14..40)
        }
    }
}