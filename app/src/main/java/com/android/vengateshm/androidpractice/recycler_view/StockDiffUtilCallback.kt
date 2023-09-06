package com.android.vengateshm.androidpractice.recycler_view

import androidx.recyclerview.widget.DiffUtil

// Older - For RecyclerView.Adapter
class StockDiffUtilCallback(
    private val oldList: List<Stock>,
    private val newList: List<Stock>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}