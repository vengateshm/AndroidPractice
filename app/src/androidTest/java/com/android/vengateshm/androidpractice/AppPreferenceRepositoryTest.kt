package com.android.vengateshm.androidpractice

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.vengateshm.androidpractice.preferences_datastore.AppPreferenceRepository
import com.android.vengateshm.androidpractice.preferences_datastore.IS_DARK_THEME
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

const val TEST_DATASTORE_NAME = "test_data_store"

@RunWith(AndroidJUnit4::class)
class AppPreferenceRepositoryTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher + Job())
    private val testContext = ApplicationProvider.getApplicationContext<Context>()
    private val testDataStorePrefs: DataStore<Preferences> = PreferenceDataStoreFactory.create(
        scope = testCoroutineScope,
        produceFile = { testContext.preferencesDataStoreFile(TEST_DATASTORE_NAME) }
    )
    private val appPrefsRepo = AppPreferenceRepository(testDataStorePrefs)

    @Before
    fun setUp() {
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @Test
    fun verifyThemeValue() {
        testCoroutineScope.runBlockingTest {
            Assert.assertEquals(false, appPrefsRepo.isDarkTheme(IS_DARK_THEME))
            appPrefsRepo.setTheme(true)
            Assert.assertEquals(false, appPrefsRepo.isDarkTheme(IS_DARK_THEME))
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
        testCoroutineScope.runBlockingTest { testDataStorePrefs.edit { it.clear() } }
        testCoroutineScope.cancel()
    }
}