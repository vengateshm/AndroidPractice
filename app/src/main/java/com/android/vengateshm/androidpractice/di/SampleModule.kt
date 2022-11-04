package com.android.vengateshm.androidpractice.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SampleModule {
    @Singleton
    @Provides
    fun provideSampleRepo(): SampleRepo {
        return SampleRepoImpl()
    }
}