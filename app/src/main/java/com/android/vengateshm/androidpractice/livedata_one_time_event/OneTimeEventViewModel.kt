package com.android.vengateshm.androidpractice.livedata_one_time_event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OneTimeEventViewModel : ViewModel() {
    private var _ldOneTimeEvent = MutableLiveData<Boolean>()
    val ldOneTimeEvent: LiveData<Boolean> = _ldOneTimeEvent

    fun sendEvent() {
        _ldOneTimeEvent.value = true
    }
}