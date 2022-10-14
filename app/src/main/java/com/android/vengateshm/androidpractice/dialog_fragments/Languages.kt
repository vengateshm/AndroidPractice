package com.android.vengateshm.androidpractice.dialog_fragments

data class Languages(val name: String) : DialogListable {
    override fun title(): String {
        return name
    }
}
