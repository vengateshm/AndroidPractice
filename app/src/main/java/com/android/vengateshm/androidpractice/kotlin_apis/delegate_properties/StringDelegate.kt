package com.android.vengateshm.androidpractice.kotlin_apis.delegate_properties

import kotlin.reflect.KProperty

class StringDelegate(private var text: String) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return text
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        text = value
    }
}

class StringDelegateHolder {
    var firstName by StringDelegate("")
}

fun main() {
    val obj = StringDelegateHolder()
    obj.firstName = "Vengatesh"
    println(obj.firstName)
}