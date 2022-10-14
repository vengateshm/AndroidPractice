package com.android.vengateshm.androidpractice.dialog_fragments

data class Technologies(val name: String) : DialogListable {
    override fun title(): String {
        return name
    }
}
