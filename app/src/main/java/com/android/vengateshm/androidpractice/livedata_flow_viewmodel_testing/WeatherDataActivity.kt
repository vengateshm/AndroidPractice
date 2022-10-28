package com.android.vengateshm.androidpractice.livedata_flow_viewmodel_testing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.vengateshm.androidpractice.databinding.ActivityWeatherDataBinding
import kotlinx.coroutines.Dispatchers

class WeatherDataActivity : AppCompatActivity() {
    private lateinit var viewModel: WeatherDataViewModel
    private lateinit var binding: ActivityWeatherDataBinding
    private val weatherDataRepository = WeatherDataRepository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            WeatherDataViewModel.WeatherDataViewModelProviderFactory(
                weatherDataRepository,
                Dispatchers.IO
            )
        )[WeatherDataViewModel::class.java]
    }
}