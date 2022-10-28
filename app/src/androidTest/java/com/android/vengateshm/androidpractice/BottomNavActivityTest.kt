package com.android.vengateshm.androidpractice

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.vengateshm.androidpractice.navigation_component.bottom_navigation.BottomNavActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BottomNavActivityTest {
    @Test
    fun testMyDialogFragment() {
        launchActivity<BottomNavActivity>().use {
            onView(withId(R.id.btnOpenDialog)).perform(click())
            onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
            onView(withId(R.id.tvShare)).check(matches(isDisplayed()))
            onView(withId(R.id.tvSave)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun testMyBottomDialogFragment() {
        launchActivity<BottomNavActivity>().use {
            onView(withId(R.id.btnOpenBottomDialog)).perform(click())
            onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
            onView(withId(R.id.tvShare)).check(matches(isDisplayed()))
            onView(withId(R.id.tvSave)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun testSampleDestinationActivity() {
        launchActivity<BottomNavActivity>().use {
            onView(withId(R.id.btnOpenActivity)).perform(click())
            onView(withText("Halo!")).check(matches(isDisplayed()))
        }
    }
}