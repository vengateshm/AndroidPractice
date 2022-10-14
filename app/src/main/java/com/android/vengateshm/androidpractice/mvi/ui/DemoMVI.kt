package com.android.vengateshm.androidpractice.mvi.ui

import com.android.vengateshm.androidpractice.mvi.BaseState
import com.android.vengateshm.androidpractice.mvi.Reducer

sealed class DemoViewActions {
    data class ShowCountryList(val countries: List<String>) : DemoViewActions()
    object ShowEmpty : DemoViewActions()
}

sealed class DemoViewState : BaseState {
    data class CountryList(val countries: List<String>) : DemoViewState()
    object EmptyView : DemoViewState()
}

class DemoViewReducer : Reducer<DemoViewActions, DemoViewState>() {
    override fun reduce(action: DemoViewActions, state: DemoViewState): DemoViewState {
        return when (action) {
            is DemoViewActions.ShowCountryList ->
                DemoViewState.CountryList(action.countries)
            is DemoViewActions.ShowEmpty ->
                DemoViewState.EmptyView
        }
    }
}