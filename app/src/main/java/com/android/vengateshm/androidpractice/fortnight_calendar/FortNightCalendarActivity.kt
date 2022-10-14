package com.android.vengateshm.androidpractice.fortnight_calendar

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.android.vengateshm.androidpractice.databinding.ActivityFortNightCalendarBinding
import java.util.*

class FortNightCalendarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFortNightCalendarBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFortNightCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fortNightCalendarView.apply {
            startDay = Calendar.getInstance()
            onDaySelected = {
                Toast.makeText(this@FortNightCalendarActivity, it.toString(), Toast.LENGTH_SHORT)
                    .show()
            }
            commit()
        }
    }
}

