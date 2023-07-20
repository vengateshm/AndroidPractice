package com.android.vengateshm.androidpractice.testing

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.vengateshm.androidpractice.R
import com.android.vengateshm.androidpractice.databinding.ActivityImageShareBinding

class ImageShareActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageShareBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityImageShareBinding.inflate(layoutInflater)

        setContentView(binding.root)

        updateButtonText()
        updateImage()
        binding.btnShare.setOnClickListener {
            startActivity(createChooserIntent())
        }
    }

    fun updateButtonText() {
        binding.btnShare.text = getString(R.string.send_btn_text)
    }

    fun updateImage() {
        binding.imgScreen.setImageURI(getShareImageUri())
    }

    fun createShareIntent(): Intent {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "image/*"
            putExtra(Intent.EXTRA_TEXT, getShareText())
            putExtra(Intent.EXTRA_STREAM, getShareImageUri())
        }
        return shareIntent
    }

    fun getShareText(): String {
        val etInputText = binding.etInput.text.toString()
        return if (etInputText.isNotEmpty()) etInputText else getString(R.string.share_empty_text)
    }

    fun createChooserIntent(): Intent {
        return Intent.createChooser(createShareIntent(), getString(R.string.title_chooser_text))
    }

    fun getShareImageUri(): Uri {
        return Uri.parse("android.resource://com.android.vengateshm.androidpractice/drawable/saturn")
    }
}