package com.android.vengateshm.androidpractice.mvi.di

import com.android.vengateshm.androidpractice.mvi.repository.DemoRepository
import com.android.vengateshm.androidpractice.mvi.repository.DemoRepositoryImpl
import com.android.vengateshm.androidpractice.mvi.ui.DemoViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val demo = module {
    single<DemoRepository> { DemoRepositoryImpl(context = androidContext()) }
    viewModel { DemoViewModel(repository = get()) }
}