package com.android.vengateshm.androidpractice.livedata_flow_viewmodel_testing

import com.android.vengateshm.androidpractice.common.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class WeatherDataRepository {
    suspend fun getWeatherByCity(cityName: String): Result<Int> {
        delay(5000)
        return Result.Success((14..40).random())
    }

    fun getWeatherByCityFlow(cityName: String) = flow {
        while (true) {
            delay(1000)
            emit(Result.Success(((14..40).random())))
        }
    }

    fun fetchWeatherForecast() = flow {
        emit(Result.Loading)
        // Fake api call
        delay(1000)
        // Send a random fake weather forecast data
        emit(Result.Success((0..20).random()))
    }
}