package com.android.vengateshm.androidpractice.di

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class SampleViewModel @AssistedInject constructor(
    private val sampleRepo: SampleRepo,
    @Assisted private val text: String,
) : ViewModel() {

    init {
        Log.i("SampleViewModel", text)
    }

    @AssistedFactory
    interface Factory {
        fun create(text: String): SampleViewModel
    }

    companion object {
        fun provideSampleViewModelFactory(
            factory: Factory,
            text: String,
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return factory.create(text) as T
                }
            }
        }
    }
}