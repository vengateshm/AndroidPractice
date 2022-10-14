package com.android.vengateshm.androidpractice.mvi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<ACTION, STATE : BaseState> : ViewModel() {
    abstract val store: BaseStore<ACTION, STATE>

    val state = MutableLiveData<STATE>()

    abstract fun bindVM()

    fun bind() { // Called inside activity - inside onCreate
        store.subscribe(stateListener = {
            state.value = it
        })
        bindVM()
    }

    fun currentState(): STATE? = state.value
}