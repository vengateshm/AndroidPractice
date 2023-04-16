package com.android.vengateshm.androidpractice

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MediatorLiveData
import com.android.vengateshm.androidpractice.testing.MainViewModel
import com.android.vengateshm.androidpractice.testing.SampleRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    lateinit var viewModel: MainViewModel

    @MockK
    lateinit var sampleRepository: SampleRepository

    @get:Rule
    val executorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = MainViewModel(sampleRepository)
    }

    @Test
    fun `test execute`() {
        every { sampleRepository.isValid() } returns true
        viewModel.execute()
        assertEquals(2, viewModel.response.value)

        every { sampleRepository.isValid() } returns false
        viewModel.execute()
        assertEquals(3, viewModel.response.value)
    }

    @Test
    fun `test execute1`() {
        viewModel = spyk(viewModel)
        val response = mockk<MediatorLiveData<Int>>()
        every { viewModel.getLiveData() } returns response
        every { response.postValue(any()) } returns Unit

        every { sampleRepository.isValid() } returns true
        viewModel.execute1()
        verify(exactly = 1) { response.postValue(2) }

        every { sampleRepository.isValid() } returns false
        viewModel.execute1()
        verify(exactly = 1) { response.postValue(2) }
    }
}