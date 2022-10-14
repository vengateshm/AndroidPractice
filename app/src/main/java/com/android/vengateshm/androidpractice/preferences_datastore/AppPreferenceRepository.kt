package com.android.vengateshm.androidpractice.preferences_datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val IS_DARK_THEME = booleanPreferencesKey("is_dark_theme")
val Context.appPrefDatastore by preferencesDataStore("app_prefs_store")

class AppPreferenceRepository(private val dataStore: DataStore<Preferences>) {
    suspend fun setTheme(isDarkTheme: Boolean) {
        dataStore.edit { appPrefs ->
            appPrefs[IS_DARK_THEME] = isDarkTheme
        }
    }

    fun isDarkTheme(key: Preferences.Key<Boolean>): Flow<Boolean?> {
        return dataStore.data.map { it[key] }
    }
}

suspend fun DataStore<Preferences>.isDarkTheme(): Boolean? {
    return this.data.first().get(IS_DARK_THEME)
}

