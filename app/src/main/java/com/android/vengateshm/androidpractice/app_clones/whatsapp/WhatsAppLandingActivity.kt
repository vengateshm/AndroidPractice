package com.android.vengateshm.androidpractice.app_clones.whatsapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.vengateshm.androidpractice.R
import com.android.vengateshm.androidpractice.databinding.ActivityWhatsAppLandingBinding
import com.google.android.material.tabs.TabLayoutMediator

class WhatsAppLandingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWhatsAppLandingBinding
    private lateinit var landingAdapter: WhatsAppLandingPageAdapter

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
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.whatsapp_toolbar_menu, menu)
        return true
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
                onMoreMenuClicked()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onCameraMenuClicked() {

    }

    private fun onSearchMenuClicked() {

    }

    private fun onMoreMenuClicked() {

    }
}