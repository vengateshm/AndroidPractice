package com.android.vengateshm.androidpractice.airbnb.mavericks.models

data class User(val firstName: String, val lastName: String) {

    fun fullName() = "${this.firstName} ${this.lastName}"

    companion object {
        fun empty() = User(
            firstName = "",
            lastName = ""
        )
    }
}
