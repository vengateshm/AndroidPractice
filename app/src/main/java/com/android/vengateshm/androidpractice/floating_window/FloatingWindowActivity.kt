package com.android.vengateshm.androidpractice.floating_window

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.vengateshm.androidpractice.R

class FloatingWindowActivity : AppCompatActivity() {

    private lateinit var minimizeBtn: Button
    private lateinit var dialog: AlertDialog
    private lateinit var descEditArea: EditText
    private lateinit var save: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_floating_window)

        minimizeBtn = findViewById(R.id.buttonMinimize)
        descEditArea = findViewById(R.id.descEditText)
        save = findViewById(R.id.saveBtn)

        if (isServiceRunning(FloatingWindowService::class.java.name)) {
            // onDestroy() method in FloatingWindowGFG
            // class will be called here
            stopService(Intent(this@FloatingWindowActivity, FloatingWindowService::class.java))
        }

        descEditArea.setText(Common.currentDesc)
        descEditArea.setSelection(descEditArea.text.toString().length)
        descEditArea.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                // Not Necessary
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                Common.currentDesc = descEditArea.text.toString()
            }

            override fun afterTextChanged(editable: Editable) {
                // Not Necessary
            }
        })

        save.setOnClickListener {
            Common.savedDesc = descEditArea.text.toString()
            descEditArea.isCursorVisible = false
            descEditArea.clearFocus()
            Toast.makeText(this@FloatingWindowActivity, "Text Saved!!!", Toast.LENGTH_SHORT).show()
        }

        minimizeBtn.setOnClickListener {
            if (checkOverlayDisplayPermission()) {
                startService(Intent(this@FloatingWindowActivity, FloatingWindowService::class.java))
                finish()
            } else {
                requestOverlayDisplayPermission {
                    val intent = Intent(
                        Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:$packageName")
                    )
                    requestOverlayPermission.launch(intent)
                }
            }
        }
    }

    private val requestOverlayPermission =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            startFloatingWindowService()
        }

    private fun startFloatingWindowService() {
        startService(Intent(this, FloatingWindowService::class.java))
    }
}