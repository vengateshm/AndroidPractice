package com.android.vengateshm.androidpractice.recycler_view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.vengateshm.androidpractice.databinding.ActivityRecyclerViewBinding
import com.android.vengateshm.androidpractice.viewpager2_transformations.ScaleInTransformer
import com.google.android.material.tabs.TabLayoutMediator

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewBinding
    private val fragmentList = listOf<Fragment>(
        StockListFragment(),
        MutualFundsListFragment()
    )
    private val tabTitles = listOf("Stocks", "Mutual Funds")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager2.setPageTransformer(ScaleInTransformer())
//        binding.viewPager2.setPageTransformer(DepthPageTransformer())
//        binding.viewPager2.setPageTransformer(RotationPageTransformer())
//        binding.viewPager2.setPageTransformer(ZoomOutPageTransformer())

        val adapter = ViewPagerAdapter(this, fragmentList)
        binding.viewPager2.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }
}

class ViewPagerAdapter(activity: AppCompatActivity, private val fragments: List<Fragment>) :
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}