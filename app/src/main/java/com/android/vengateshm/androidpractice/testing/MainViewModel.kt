package com.android.vengateshm.androidpractice.testing

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(private val repository: SampleRepository) : ViewModel() {
    val response = MediatorLiveData<Int>()

    fun execute() {
        if (repository.isValid()) {
            response.postValue(2)
        } else {
            response.postValue(3)
        }
    }

    fun execute1() {
        if (repository.isValid()) {
            getLiveData().postValue(2)
        } else {
            getLiveData().postValue(3)
        }
    }

    fun getLiveData(): MediatorLiveData<Int> {
        return response
    }
}