package com.android.vengateshm.androidpractice.di

class SampleRepoImpl : SampleRepo {
    override suspend fun getSampleString(): String {
        return "Sample"
    }
}