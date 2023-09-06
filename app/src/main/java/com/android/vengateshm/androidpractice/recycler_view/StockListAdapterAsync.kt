package com.android.vengateshm.androidpractice.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.vengateshm.androidpractice.databinding.StockListItemBinding

class StockListAdapterAsync : RecyclerView.Adapter<StockListAdapterAsync.ViewHolder>() {

    val asyncListDiffer = AsyncListDiffer(this, object : DiffUtil.ItemCallback<Stock>() {
        override fun areItemsTheSame(oldItem: Stock, newItem: Stock): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Stock, newItem: Stock): Boolean {
            return oldItem == newItem
        }
    })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            StockListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return asyncListDiffer.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val stock = asyncListDiffer.currentList[position]
        holder.bindItem(stock)
    }

    /*override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isNotEmpty()) {
            // Partial update using payloads (only update price)
            val updatedStock = payloads[0] as Stock
            holder.bindPartial {
                tvPrice.text = updatedStock.price.toString()
            }
        } else {
            // Full bind if no payloads
            super.onBindViewHolder(holder, position, payloads)
        }
    }*/

    fun submitList(stocks: List<Stock>) {
        asyncListDiffer.submitList(stocks)
    }

    inner class ViewHolder(private val binding: StockListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(stock: Stock) {
            binding.tvName.text = stock.name
            binding.tvSymbol.text = stock.symbol
            binding.tvPrice.text = stock.price.toString()
        }

        fun bindPartial(callback: StockListItemBinding.() -> Unit) {
            callback.invoke(binding)
        }
    }
}