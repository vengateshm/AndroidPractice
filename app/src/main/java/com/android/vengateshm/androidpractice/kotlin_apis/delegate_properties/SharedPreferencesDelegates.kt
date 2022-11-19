package com.android.vengateshm.androidpractice.kotlin_apis.delegate_properties

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/*operator fun SharedPreferences.getValue(thisRef: Any?, property: KProperty<*>): Boolean {
    return getBoolean(property.name, false)
}

operator fun SharedPreferences.setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) {
    edit { putBoolean(property.name, value) }
}*/

/*inline operator fun <reified T> SharedPreferences.getValue(
    thisRef: Any?,
    property: KProperty<*>,
): T {
    return when (T::class) {
        Boolean::class -> getBoolean(property.name, false)
        Int::class -> getInt(property.name, 0)
        Float::class -> getFloat(property.name, 0f)
        Long::class -> getLong(property.name, 0L)
        String::class -> getString(property.name, "")
        else -> throw UnsupportedOperationException()
    } as T
}

operator fun <T> SharedPreferences.setValue(thisRef: Any?, property: KProperty<*>, value: T) {
    edit {
        when (value) {
            is Boolean -> putBoolean(property.name, value)
            is Int -> putInt(property.name, value)
            is Float -> putFloat(property.name, value)
            is Long -> putLong(property.name, value)
            is String -> putString(property.name, value)
            else -> throw UnsupportedOperationException()
        }
    }
}*/

fun SharedPreferences.booleanDelegate() = object : ReadWriteProperty<Any, Boolean> {
    override fun getValue(thisRef: Any, property: KProperty<*>): Boolean {
        return getBoolean(property.name, false)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
        edit {
            putBoolean(property.name, value)
        }
    }
}

fun SharedPreferences.intDelegate() = object : ReadWriteProperty<Any, Int> {
    override fun getValue(thisRef: Any, property: KProperty<*>): Int {
        return getInt(property.name, 0)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) {
        edit {
            putInt(property.name, value)
        }
    }
}

class SharedPreferencesDelegates(private val preferences: SharedPreferences) {
    /*fun boolean() = object : ReadWriteProperty<Any, Boolean> {
        override fun getValue(thisRef: Any, property: KProperty<*>): Boolean {
            return preferences.getBoolean(property.name, false)
        }

        override fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
            preferences.edit {
                putBoolean(property.name, value)
            }
        }
    }

    fun int() = object : ReadWriteProperty<Any, Int> {
        override fun getValue(thisRef: Any, property: KProperty<*>): Int {
            return preferences.getInt(property.name, 0)
        }

        override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) {
            preferences.edit {
                putInt(property.name, value)
            }
        }
    }*/

    fun boolean(default: Boolean = false, key: String? = null) = create(
        default,
        key,
        preferences::getBoolean,
        preferences.edit()::putBoolean
    )

    fun int(default: Int = 0, key: String? = null) = create(
        default,
        key,
        preferences::getInt,
        preferences.edit()::putInt
    )

    fun long(default: Long = 0L, key: String? = null) = create(
        default,
        key,
        preferences::getLong,
        preferences.edit()::putLong
    )

    fun float(default: Float = 0f, key: String? = null) = create(
        default,
        key,
        preferences::getFloat,
        preferences.edit()::putFloat
    )

    fun string(default: String = "", key: String? = null) = create(
        default,
        key,
        { k, d -> preferences.getString(k, d) as String },
        preferences.edit()::putString
    )

    fun stringSet(default: Set<String> = emptySet(), key: String? = null) = create(
        default,
        key,
        { k, d -> preferences.getStringSet(k, d) as Set<String> },
        preferences.edit()::putStringSet
    )

    private fun <T : Any> create(
        default: T,
        key: String? = null,
        getter: (key: String, value: T) -> T,
        setter: (key: String, value: T) -> SharedPreferences.Editor,
    ) = object : ReadWriteProperty<Any, T> {

        private fun key(property: KProperty<*>) = key ?: property.name

        override fun getValue(thisRef: Any, property: KProperty<*>): T {
            return getter(key(property), default)
        }

        override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
            setter(key(property), value).apply()
        }
    }
}

val SharedPreferences.delegates: SharedPreferencesDelegates
    get() = SharedPreferencesDelegates(this)

class SettingsRepository(preferences: SharedPreferences) {
    /*var firstName: String by preferences
    var isDarkTheme: Boolean by preferences*/
    /*var isDarkTheme by preferences.booleanDelegate()
    var notificationCount by preferences.intDelegate()*/
    var isDarkTheme by preferences.delegates.boolean(true)
    var notificationCount by preferences.delegates.int(3)
    var firstName: String by preferences.delegates.string("")
    val ids: Set<String> by preferences.delegates.stringSet(emptySet())
}