package com.android.vengateshm.androidpractice.constraint_layout

import android.animation.ValueAnimator
import android.os.Bundle
import android.transition.TransitionManager
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.android.vengateshm.androidpractice.R
import com.android.vengateshm.androidpractice.databinding.ClCircularConstraintPlanetOrbitBinding

class ConstraintLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        Handler(Looper.getMainLooper()).postDelayed({
//            val binding = LayoutWithBarrierBinding.inflate(layoutInflater)
//            setContentView(binding.root)
//
//            binding.tvFirstName.text = "Sachin"
//            binding.tvLastName.text = "Tendulkar"
//        }, 5000)

        planetOrbitAndDetails()
    }

    private fun planetOrbitAndDetails() {
        val orbitConstraintSet = ConstraintSet()
        val detailsConstraintSet = ConstraintSet()

        val binding = ClCircularConstraintPlanetOrbitBinding.inflate(layoutInflater)
        orbitConstraintSet.clone(binding.root)
        detailsConstraintSet.clone(this, R.layout.cl_planet_details)
        setContentView(binding.root)

        animatePlanet(binding.ivEarth, 2000).start()
        animatePlanet(binding.ivMars, 6000).start()
        animatePlanet(binding.ivSaturn, 12000).start()

        binding.ivSun.setOnClickListener {
            TransitionManager.beginDelayedTransition(binding.root)
            detailsConstraintSet.applyTo(binding.root)
        }
    }

    private fun animatePlanet(imageView: ImageView, orbitDuration: Long): ValueAnimator {
        val valueAnimator = ValueAnimator.ofInt(0, 359)
        valueAnimator.addUpdateListener {
            val angle = it.animatedValue as Int
            val lp = (imageView.layoutParams as ConstraintLayout.LayoutParams)
            lp.circleAngle = angle.toFloat()
            imageView.layoutParams = lp
        }
        valueAnimator.duration = orbitDuration
        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.repeatMode = ValueAnimator.RESTART
        valueAnimator.repeatCount = ValueAnimator.INFINITE
        return valueAnimator
    }
}