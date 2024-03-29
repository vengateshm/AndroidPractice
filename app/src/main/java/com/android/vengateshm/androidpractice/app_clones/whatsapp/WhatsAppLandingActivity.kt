package com.android.vengateshm.androidpractice.app_clones.whatsapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.android.vengateshm.androidpractice.R
import com.android.vengateshm.androidpractice.databinding.ActivityWhatsAppLandingBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class WhatsAppLandingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWhatsAppLandingBinding
    private lateinit var landingAdapter: WhatsAppLandingPageAdapter
    private var currentTabPosition = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWhatsAppLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""

        landingAdapter = WhatsAppLandingPageAdapter(this)
        binding.viewPager.adapter = landingAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.customView = layoutInflater.inflate(R.layout.whatsapp_tab_icon, null)
                1 -> tab.customView =
                    layoutInflater.inflate(R.layout.whatsapp_tab_text, null).apply {
                        (this@apply as? TextView)?.text = "Chats"
                    }

                2 -> tab.customView =
                    layoutInflater.inflate(R.layout.whatsapp_tab_text, null).apply {
                        (this@apply as? TextView)?.text = "Updates"
                    }

                3 -> tab.customView =
                    layoutInflater.inflate(R.layout.whatsapp_tab_text, null)
                        .apply { (this@apply as? TextView)?.text = "Calls" }

                else -> error("Unknown position")
            }
        }.attach()

        binding.tabLayout.setTabWidthAsWrapContent(0)

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                currentTabPosition = position
                invalidateOptionsMenu()
                setFabVisibility(position)
            }
        })
    }

    private fun setFabVisibility(position: Int) {
        when (position) {
            0 -> {
                binding.fabNewTextStatus.isVisible = false
                binding.fabNewStatus.isVisible = false
            }

            1 -> {
                animateFabByViewProperty(false, binding.fabNewTextStatus)
                binding.fabNewStatus.isVisible = true
                binding.fabNewStatus.setImageResource(R.drawable.round_chat_24)
            }

            2 -> {
                animateFabByViewProperty(true, binding.fabNewTextStatus)
                binding.fabNewStatus.isVisible = true
                binding.fabNewStatus.setImageResource(R.drawable.round_photo_camera_24)
            }

            3 -> {
                animateFabByViewProperty(false, binding.fabNewTextStatus)
                binding.fabNewTextStatus.isVisible = false
                binding.fabNewStatus.isVisible = true
                binding.fabNewStatus.setImageResource(R.drawable.baseline_add_ic_call_24)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.whatsapp_toolbar_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        when (binding.tabLayout.selectedTabPosition) {
            0, 2 -> {
                menu?.findItem(R.id.mnuCamera)?.isVisible = true
                menu?.findItem(R.id.mnuSearch)?.isVisible = false
                menu?.findItem(R.id.mnuMore)?.isVisible = true
            }

            else -> {
                menu?.findItem(R.id.mnuCamera)?.isVisible = true
                menu?.findItem(R.id.mnuSearch)?.isVisible = true
                menu?.findItem(R.id.mnuMore)?.isVisible = true
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mnuCamera -> {
                onCameraMenuClicked()
                true
            }

            R.id.mnuSearch -> {
                onSearchMenuClicked()
                true
            }

            R.id.mnuMore -> {
                this@WhatsAppLandingActivity.findViewById<View>(item.itemId)?.let { moreView ->
                    onMoreMenuClicked(moreView)
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onCameraMenuClicked() {

    }

    private fun onSearchMenuClicked() {

    }

    private fun onMoreMenuClicked(view: View) {
        showPopupMenu(view)
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.whatsapp_more_menu, popupMenu.menu)

        if (currentTabPosition != 1) {
            popupMenu.menu.findItem(R.id.menu_new_group).isVisible = false
            popupMenu.menu.findItem(R.id.menu_new_broadcast).isVisible = false
            popupMenu.menu.findItem(R.id.menu_linked_devices).isVisible = false
            popupMenu.menu.findItem(R.id.menu_starred_messages).isVisible = false
            popupMenu.menu.findItem(R.id.menu_payments).isVisible = false
            popupMenu.menu.findItem(R.id.menu_settings).isVisible = true
        }

        // Set up the click listener for the popup menu items
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_new_group -> {
                    // Handle New Group click
                    // Replace this with your own logic
                    true
                }

                R.id.menu_new_broadcast -> {
                    // Handle New Broadcast click
                    // Replace this with your own logic
                    true
                }

                R.id.menu_linked_devices -> {
                    // Handle Linked Devices click
                    // Replace this with your own logic
                    true
                }

                R.id.menu_starred_messages -> {
                    // Handle Starred Messages click
                    // Replace this with your own logic
                    true
                }

                R.id.menu_payments -> {
                    // Handle Payments click
                    // Replace this with your own logic
                    true
                }

                R.id.menu_settings -> {
                    // Handle Settings click
                    // Replace this with your own logic
                    true
                }

                else -> false
            }
        }

        popupMenu.show()
    }
}

fun TabLayout.setTabWidthAsWrapContent(tabPosition: Int) {
    val layout = (this.getChildAt(0) as LinearLayout).getChildAt(tabPosition) as LinearLayout
    val layoutParams = layout.layoutParams as LinearLayout.LayoutParams
    layoutParams.weight = 0f
    layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
    layout.layoutParams = layoutParams
}