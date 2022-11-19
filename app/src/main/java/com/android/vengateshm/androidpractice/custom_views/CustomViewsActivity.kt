package com.android.vengateshm.androidpractice.custom_views

import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.android.vengateshm.androidpractice.R

class CustomViewsActivity : AppCompatActivity() {

    private lateinit var seekBar: SeekBar
    private lateinit var ft1: FittingToolbar
    private lateinit var ft2: FittingToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_views)

        seekBar = findViewById(R.id.seekBar)
        ft1 = findViewById(R.id.ft1)
        ft2 = findViewById(R.id.ft2)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                ft1.layoutParams.width = p1 * 25
                ft2.layoutParams.width = p1 * 25
                ft1.requestLayout()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })
    }
}