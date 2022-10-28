package com.android.vengateshm.androidpractice.payment_integration.razorPay.android_sdk

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.android.vengateshm.androidpractice.R
import com.razorpay.Checkout

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        Checkout.preload(this)

        with(findViewById<Button>(R.id.btnCreateOrder)) {
            setOnClickListener {
                Intent(this@OrderActivity, MyPaymentActivity::class.java)
                    .also {
                        startActivity(it)
                    }
            }
        }
    }
}