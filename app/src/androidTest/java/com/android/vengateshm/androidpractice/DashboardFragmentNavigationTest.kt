package com.android.vengateshm.androidpractice

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.vengateshm.androidpractice.navigation_component.bottom_navigation.DashboardFragment
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DashboardFragmentNavigationTest {

    @Test
    fun testNavigationToMyDialogFragment() {
        // Create a TestNavHostController
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        // Create a graphical FragmentScenario for the DashboardFragment
        val scenario = launchFragmentInContainer<DashboardFragment>()
        scenario.onFragment { fragment ->
            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.bottom_nav_graph)

            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        onView(withId(R.id.btnOpenDialog)).perform(click())
        val name = navController.currentDestination?.label
        Assert.assertEquals("MyDialogFragment", name)
    }

    @Test
    fun testNavigationToMyBottomDialogFragment() {
        // Create a TestNavHostController
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        // Create a graphical FragmentScenario for the DashboardFragment
        val scenario = launchFragmentInContainer<DashboardFragment>()
        scenario.onFragment { fragment ->
            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.bottom_nav_graph)

            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        onView(withId(R.id.btnOpenBottomDialog)).perform(click())
        val name = navController.currentDestination?.label
        Assert.assertEquals("MyBottomDialogFragment", name)
    }

    @Test
    fun testNavigationToSampleDestinationActivity() {
        // Create a TestNavHostController
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        // Create a graphical FragmentScenario for the DashboardFragment
        val scenario = launchFragmentInContainer<DashboardFragment>()
        scenario.onFragment { fragment ->
            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.bottom_nav_graph)

            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        onView(withId(R.id.btnOpenActivity)).perform(click())
        val name = navController.currentDestination?.label
        Assert.assertEquals("SampleDestinationActivity", name)
    }
}