package com.android.vengateshm.androidpractice

import com.android.vengateshm.androidpractice.testing.ISchedulerProvider
import com.android.vengateshm.androidpractice.testing.NetworkApi
import com.android.vengateshm.androidpractice.testing.NetworkDataSource
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

class NetworkDataSourceTest {

    private val dataSource = NetworkDataSource()

    // Alternative : create test rule
    private val immediateScheduler = object : Scheduler() {
        override fun createWorker(): Worker {
            return ExecutorScheduler.ExecutorWorker({ it.run() }, true)
        }
    }

    /*@get:Rule
    val schedulerRule = RxImmediateSchedulerRule()*/

    @Before
    fun setUp() {
        RxJavaPlugins.setInitIoSchedulerHandler { immediateScheduler }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediateScheduler }
    }

    @After
    fun tearDown() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    @Test
    fun testGetTodos() {
        mockkObject(NetworkApi)
        val api: NetworkApi = mockk()
        every { NetworkApi.create() } returns api
        every { dataSource.getTodo() } returns Single.just(listOf())
        val result = dataSource.getTodo().test()
        result.assertComplete()
        result.assertNoErrors()
        Assert.assertTrue(result.values().first().isEmpty())
        result.dispose()
    }

    @Test
    fun testGetTodos1() {
        mockkObject(NetworkApi)
        val api: NetworkApi = mockk()
        every { NetworkApi.create() } returns api

        val schedulerProvider : ISchedulerProvider = mockk()
        every { schedulerProvider.io() } returns Schedulers.trampoline()
        every { schedulerProvider.main() } returns Schedulers.trampoline()

        every { dataSource.getTodo(schedulerProvider) } returns Single.just(listOf())

        val result = dataSource.getTodo(schedulerProvider).test()

        result.assertComplete()
        result.assertNoErrors()
        Assert.assertTrue(result.values().first().isEmpty())
        result.dispose()
    }

    @Test
    fun testGetTodosError() {
        mockkObject(NetworkApi)
        val api: NetworkApi = mockk()
        every { NetworkApi.create() } returns api
        val throwable = Throwable()
        every { dataSource.getTodo() } returns Single.error(throwable)
        val result = dataSource.getTodo().test()
        result.assertError(throwable)
        result.dispose()
    }

    @Test
    fun testGetTodosError1() {
        mockkObject(NetworkApi)
        val api: NetworkApi = mockk()
        every { NetworkApi.create() } returns api
        val throwable = Throwable()

        val schedulerProvider : ISchedulerProvider = mockk()
        every { schedulerProvider.io() } returns Schedulers.trampoline()
        every { schedulerProvider.main() } returns Schedulers.trampoline()

        every { dataSource.getTodo() } returns Single.error(throwable)

        val result = dataSource.getTodo().test()
        result.assertError(throwable)

        result.dispose()
    }
}