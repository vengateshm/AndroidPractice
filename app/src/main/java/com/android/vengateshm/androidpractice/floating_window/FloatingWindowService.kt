package com.android.vengateshm.androidpractice.floating_window

import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.vengateshm.androidpractice.R

class FloatingWindowService : Service() {
    private var floatView: ViewGroup? = null
    private var layoutType = 0
    private var floatWindowLayoutParam: WindowManager.LayoutParams? = null
    private lateinit var windowManager: WindowManager
    private lateinit var maximizeBtn: Button
    private lateinit var descEditArea: EditText
    private lateinit var saveBtn: Button

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        val metrics = applicationContext.resources.displayMetrics
        val width = metrics.widthPixels
        val height = metrics.heightPixels

        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager

        val inflater = baseContext.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater

        floatView = inflater.inflate(R.layout.floating_window_layout, null) as ViewGroup?

        maximizeBtn = floatView!!.findViewById(R.id.buttonMaximize)
        descEditArea = floatView!!.findViewById(R.id.descEditText)
        saveBtn = floatView!!.findViewById(R.id.saveBtn)

        descEditArea.setText(Common.currentDesc)
        descEditArea.setSelection(descEditArea.text.toString().length)
        descEditArea.isCursorVisible = false

        layoutType = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // If API Level is more than 26, we need TYPE_APPLICATION_OVERLAY
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        } else {
            // If API Level is lesser than 26, then we can
            // use TYPE_SYSTEM_ERROR,
            // TYPE_SYSTEM_OVERLAY, TYPE_PHONE, TYPE_PRIORITY_PHONE.
            // But these are all
            // deprecated in API 26 and later. Here TYPE_TOAST works best.
            WindowManager.LayoutParams.TYPE_TOAST
        }

        floatWindowLayoutParam = WindowManager.LayoutParams(
            (width * 0.55f).toInt(), (height * 0.58f).toInt(),
            layoutType,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, // Key inputs can't be given to the EditText.
            PixelFormat.TRANSLUCENT
        )

        floatWindowLayoutParam!!.gravity = Gravity.CENTER

        floatWindowLayoutParam!!.x = 0
        floatWindowLayoutParam!!.y = 0

        windowManager.addView(floatView, floatWindowLayoutParam)

        maximizeBtn.setOnClickListener { // stopSelf() method is used to stop the service if
            stopSelf()

            windowManager.removeView(floatView)

            val backToHome = Intent(
                this@FloatingWindowService,
                FloatingWindowActivity::class.java
            )
            backToHome.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(backToHome)
        }

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

        floatView!!.setOnTouchListener(object : View.OnTouchListener {
            val floatWindowLayoutUpdateParam: WindowManager.LayoutParams =
                floatWindowLayoutParam as WindowManager.LayoutParams
            var x = 0.0
            var y = 0.0
            var px = 0.0
            var py = 0.0
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        x = floatWindowLayoutUpdateParam.x.toDouble()
                        y = floatWindowLayoutUpdateParam.y.toDouble()

                        // returns the original raw X
                        // coordinate of this event
                        px = event.rawX.toDouble()

                        // returns the original raw Y
                        // coordinate of this event
                        py = event.rawY.toDouble()
                    }

                    MotionEvent.ACTION_MOVE -> {
                        floatWindowLayoutUpdateParam.x = (x + event.rawX - px).toInt()
                        floatWindowLayoutUpdateParam.y = (y + event.rawY - py).toInt()

                        windowManager.updateViewLayout(floatView, floatWindowLayoutUpdateParam)
                    }
                }
                return false
            }
        })

        // Floating Window Layout Flag is set to FLAG_NOT_FOCUSABLE,
        // so no input is possible to the EditText. But that's a problem.
        // So, the problem is solved here. The Layout Flag is
        // changed when the EditText is touched.
        descEditArea.setOnTouchListener { v, event ->
            descEditArea.isCursorVisible = true
            val floatWindowLayoutParamUpdateFlag: WindowManager.LayoutParams =
                floatWindowLayoutParam as WindowManager.LayoutParams
            // Layout Flag is changed to FLAG_NOT_TOUCH_MODAL which
            // helps to take inputs inside floating window, but
            // while in EditText the back button won't work and
            // FLAG_LAYOUT_IN_SCREEN flag helps to keep the window
            // always over the keyboard
            floatWindowLayoutParamUpdateFlag.flags =
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN

            windowManager!!.updateViewLayout(floatView, floatWindowLayoutParamUpdateFlag)
            false
        }
        saveBtn.setOnClickListener { // saves the text in savedDesc variable
            Common.savedDesc = descEditArea.getText().toString()
            descEditArea.isCursorVisible = false
            val floatWindowLayoutParamUpdateFlag: WindowManager.LayoutParams =
                floatWindowLayoutParam as WindowManager.LayoutParams
            floatWindowLayoutParamUpdateFlag.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE

            // The Layout Flag is changed back to FLAG_NOT_FOCUSABLE. and the Layout is updated with new Flag
            windowManager.updateViewLayout(floatView, floatWindowLayoutParamUpdateFlag)

            // INPUT_METHOD_SERVICE with Context is used
            // to retrieve a InputMethodManager for
            // accessing input methods which is the soft keyboard here
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

            // The soft keyboard slides back in
            inputMethodManager.hideSoftInputFromWindow(floatView!!.applicationWindowToken, 0)

            // A Toast is shown when the text is saved
            Toast.makeText(this@FloatingWindowService, "Text Saved!!!", Toast.LENGTH_SHORT).show()
        }
    }

    // It is called when stopService()
    // method is called in MainActivity
    override fun onDestroy() {
        super.onDestroy()
        stopSelf()
        // Window is removed from the screen
        windowManager!!.removeView(floatView)
    }
}