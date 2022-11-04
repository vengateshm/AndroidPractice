package com.android.vengateshm.androidpractice.di

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SampleDiActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: SampleViewModel.Factory

    private val viewModel: SampleViewModel by viewModels {
        SampleViewModel.provideSampleViewModelFactory(factory, "Hello")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel
    }
}