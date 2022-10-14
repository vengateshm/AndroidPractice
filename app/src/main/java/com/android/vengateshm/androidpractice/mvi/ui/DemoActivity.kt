package com.android.vengateshm.androidpractice.mvi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.vengateshm.androidpractice.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class DemoActivity : AppCompatActivity() {

    private val viewModel: DemoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)

        listenToState()
        viewModel.bind()
    }

    private fun listenToState() {
        viewModel.state.observe(this) { state ->
            renderState(state = state)
        }
    }

    private fun renderState(state: DemoViewState) {
        when (state) {
            is DemoViewState.CountryList -> showCountries(state.countries)
            is DemoViewState.EmptyView -> showEmpty()
        }
    }

    private fun showCountries(countries: List<String>) {
        println(countries)
    }

    private fun showEmpty() {
        println("Empty")
    }
}