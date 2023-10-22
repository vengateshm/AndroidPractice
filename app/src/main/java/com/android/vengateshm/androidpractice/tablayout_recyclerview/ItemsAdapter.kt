package com.android.vengateshm.androidpractice.tablayout_recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.vengateshm.androidpractice.databinding.TablayoutRecyclerviewItemBinding

class ItemsAdapter : RecyclerView.Adapter<ItemsAdapter.ItemVH>() {

    var items = mutableListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVH {
        val binding = TablayoutRecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ItemVH(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemVH, position: Int) {
        holder.bindItem(items[position])
    }

    inner class ItemVH(private val binding: TablayoutRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(item: Item) {
            binding.tvName.text = item.name
        }
    }
}