package com.android.vengateshm.androidpractice.observable_types

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ObservableTypesViewModel : ViewModel() {
    private val _mLiveData = MutableLiveData("Bonjour LiveData!")
    val mLiveData: LiveData<String> = _mLiveData

    private val _mStateFlow = MutableStateFlow("Bonjour StateFlow!")
    val mStateFlow = _mStateFlow.asStateFlow() // Making it readonly

    private val _mSharedFlow = MutableSharedFlow<String>()
    val mSharedFlow = _mSharedFlow.asSharedFlow() // Making it readonly

    fun triggerLiveData() {
        _mLiveData.value = "LiveData"
    }

    fun triggerStateFlow() {
        _mStateFlow.value = "StateFlow"
    }

    fun triggerFlow(): Flow<String> {
        return flow {
            repeat(5) {
                emit("Item $it")
                kotlinx.coroutines.delay(1000L)
            }
        }
    }

    fun triggerSharedFlow() {
        viewModelScope.launch {
            _mSharedFlow.emit("SharedFlow")
        }
    }
}