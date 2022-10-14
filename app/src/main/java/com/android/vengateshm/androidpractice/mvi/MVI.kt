package com.android.vengateshm.androidpractice.mvi

interface BaseState

abstract class Reducer<ACTION, STATE : BaseState> {
    abstract fun reduce(action: ACTION, state: STATE): STATE
}

class BaseStore<ACTION, STATE : BaseState>(
    initialState: STATE,
    private val reducer: Reducer<ACTION, STATE>,
) {

    private var stateListener: ((STATE) -> Unit)? = null

    private var currentState = initialState
        set(value) {
            field = value
            stateListener?.invoke(value)
        }

    fun dispatch(action: ACTION) {
        currentState = reducer.reduce(action, currentState)
    }

    fun subscribe(stateListener: ((STATE) -> Unit)?) {
        this.stateListener = stateListener
    }
}