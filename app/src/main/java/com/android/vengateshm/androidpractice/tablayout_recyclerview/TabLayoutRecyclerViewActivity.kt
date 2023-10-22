package com.android.vengateshm.androidpractice.tablayout_recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.vengateshm.androidpractice.databinding.ActivityTablayoutRecyclerviewBinding
import com.google.android.material.tabs.TabLayout

class TabLayoutRecyclerViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTablayoutRecyclerviewBinding

    private lateinit var adapter: ItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTablayoutRecyclerviewBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val categories = listOf(
            Category("Category 1"),
            Category("Category 2"),
            Category("Category 3"),
            Category("Category 4"),
            Category("Category 5")
        )

        val items = listOf(
            Item("Item 1", "Category 1"),
            Item("Item 2", "Category 1"),
            Item("Item 3", "Category 1"),
            Item("Item 4", "Category 1"),
            Item("Item 5", "Category 1"),
            Item("Item 6", "Category 2"),
            Item("Item 7", "Category 2"),
            Item("Item 8", "Category 2"),
            Item("Item 9", "Category 2"),
            Item("Item 10", "Category 2"),
            Item("Item 11", "Category 3"),
            Item("Item 12", "Category 3"),
            Item("Item 13", "Category 3"),
            Item("Item 14", "Category 3"),
            Item("Item 15", "Category 3"),
            Item("Item 16", "Category 4"),
            Item("Item 17", "Category 4"),
            Item("Item 18", "Category 4"),
            Item("Item 19", "Category 4"),
            Item("Item 20", "Category 4"),
            Item("Item 21", "Category 5"),
            Item("Item 22", "Category 5"),
            Item("Item 23", "Category 5"),
            Item("Item 24", "Category 5"),
            Item("Item 25", "Category 5"),
        )

        adapter = ItemsAdapter()
        adapter.items = items.toMutableList()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        categories.forEach { category ->
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(category.name))
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val categoryName = tab?.text.toString() ?: ""
                val index = items.indexOfFirst { it.category == categoryName }
                if (index != -1) {
                    binding.recyclerView.scrollToPosition(index)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val firstVisibleItemPosition =
                    (binding.recyclerView.layoutManager as LinearLayoutManager)
                        .findFirstVisibleItemPosition()
                if (firstVisibleItemPosition != RecyclerView.NO_POSITION) {
                    val category = items[firstVisibleItemPosition].category
                    val tab =
                        binding.tabLayout.getTabAt(categories.indexOfFirst { it.name == category })
                    tab?.select()
                }
            }
        })
    }
}