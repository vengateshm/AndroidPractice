package com.android.vengateshm.androidpractice

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.vengateshm.androidpractice.common.Result
import com.android.vengateshm.androidpractice.livedata_flow_viewmodel_testing.WeatherDataRepository
import com.android.vengateshm.androidpractice.livedata_flow_viewmodel_testing.WeatherDataViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WeatherDataViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

//    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    @ExperimentalCoroutinesApi
//    @Test
//    fun weatherForecastLiveData_ShouldPostLoadingThenSuccess() {
//        val weatherDataRepository = WeatherDataRepository()
//        val viewModel = WeatherDataViewModel(
//            weatherDataRepository,
//            mainCoroutineRule.tesCoroutineDispatcher
//        )
//        mainCoroutineRule.tesCoroutineDispatcher.runBlockingTest {
//            viewModel.fetchWeatherForecast()
//
//            // Check whether the first value is loading
//            assert(viewModel.weatherForecast.value == Result.Loading)
//
////            // Wait for the response
//            delay(5000)
//
//            // Check whether the response is successful
//            assert(viewModel.weatherForecast.value is Result.Success)
//        }
//    }

    @InternalCoroutinesApi
    @ExperimentalCoroutinesApi
    @Test
    fun fetchWeatherForecast_ShouldReturnLoadingThenResult_collect() {
        val weatherDataRepository = WeatherDataRepository()
        val viewModel = WeatherDataViewModel(
            weatherDataRepository,
            mainCoroutineRule.tesCoroutineDispatcher
        )
        mainCoroutineRule.tesCoroutineDispatcher.runBlockingTest {
//            val weatherForecastFlow = weatherDataRepository.fetchWeatherForecast()
//            weatherForecastFlow.collectIndexed { index, value ->
//                {
//                    // Check whether first data is loading
//                    if (index == 0) assert(value == Result.Loading)
//
//                    // Check whether second data is Success
//                    if (index == 1) assert(value is Result.Success)
//                }
//            }

            // List to keep weather forecast values
            val weatherForecastList = mutableListOf<Result<Int>>()

            // Make api call
            val weatherForecastFlow = weatherDataRepository.fetchWeatherForecast()

            // Convert flow to a list
            weatherForecastFlow.toList(weatherForecastList)

            // Check whether first data is loading
            assert(weatherForecastList.first() == Result.Loading)

            // Check whether second (last) data is Success
            assert(weatherForecastList.last() is Result.Success)
        }
    }
}