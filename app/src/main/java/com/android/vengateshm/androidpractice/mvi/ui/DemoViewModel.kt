package com.android.vengateshm.androidpractice.mvi.ui

import com.android.vengateshm.androidpractice.mvi.BaseStore
import com.android.vengateshm.androidpractice.mvi.BaseViewModel
import com.android.vengateshm.androidpractice.mvi.repository.DemoRepository

class DemoViewModel(private val repository: DemoRepository) :
    BaseViewModel<DemoViewActions, DemoViewState>() {
    override val store: BaseStore<DemoViewActions, DemoViewState> =
        BaseStore(
            initialState = DemoViewState.EmptyView,
            reducer = DemoViewReducer()
        )

    override fun bindVM() {
        countries()
    }

    private fun countries() {
        val countries = repository.countries()
            .map { countryName ->
                countryName.substringAfter("_")
                    .substringBefore(".")
            }
        store.dispatch(DemoViewActions.ShowCountryList(countries = countries))
    }
}

