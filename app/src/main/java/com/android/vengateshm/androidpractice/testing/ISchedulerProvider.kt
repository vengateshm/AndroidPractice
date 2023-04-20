package com.android.vengateshm.androidpractice.testing

import io.reactivex.Scheduler

interface ISchedulerProvider {
    fun io(): Scheduler
    fun computation(): Scheduler
    fun main(): Scheduler
}