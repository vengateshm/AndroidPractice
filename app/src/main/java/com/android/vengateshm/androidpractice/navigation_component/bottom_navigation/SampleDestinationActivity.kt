package com.android.vengateshm.androidpractice.navigation_component.bottom_navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.vengateshm.androidpractice.databinding.ActivitySampleDestinationBinding

class SampleDestinationActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySampleDestinationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySampleDestinationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent?.extras?.let { bundle ->
            val text = bundle.getString("text") ?: "Hello!"
            binding.tvTextView.text = text
        }
    }
}