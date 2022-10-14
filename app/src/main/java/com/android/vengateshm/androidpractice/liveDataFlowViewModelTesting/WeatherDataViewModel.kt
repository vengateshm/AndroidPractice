package com.android.vengateshm.androidpractice.liveDataFlowViewModelTesting

import androidx.lifecycle.*
import com.android.vengateshm.androidpractice.common.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class WeatherDataViewModel(
    private val weatherDataRepository: WeatherDataRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private var _weatherForecast = MutableLiveData<Result<Int>>()
    public val weatherForecast: LiveData<Result<Int>> = _weatherForecast

    fun fetchWeatherForecast() {
        _weatherForecast.value = Result.Loading
        viewModelScope.launch(dispatcher) {
            _weatherForecast.value = weatherDataRepository.getWeatherByCity("")
        }
    }

    class WeatherDataViewModelProviderFactory(
        private val weatherDataRepository: WeatherDataRepository,
        private val dispatcher: CoroutineDispatcher
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WeatherDataViewModel::class.java)) {
                return WeatherDataViewModel(weatherDataRepository, dispatcher) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}