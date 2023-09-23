package com.android.vengateshm.androidpractice.app_clones.whatsapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class WhatsAppLandingPageAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 4;
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CommunityFragment()
            1 -> ChatsFragment()
            2 -> UpdatesFragment()
            3 -> CallsFragment()
            else -> error("Unknown position")
        }
    }
}