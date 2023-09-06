package com.android.vengateshm.androidpractice.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.vengateshm.androidpractice.databinding.StockListItemBinding

class StockListAdapter : RecyclerView.Adapter<StockListAdapter.ViewHolder>() {

    var stockList = mutableListOf<Stock>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            StockListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return stockList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val stock = stockList[position]
        holder.bindItem(stock)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
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
    }

    // Update the data for a specific item with a payload
    fun updateItemWithPayload(position: Int, newStock: Stock) {
        stockList[position] = newStock
        notifyItemChanged(position, newStock) // Pass the payload here
    }

    // Update the data with DiffUtil
    fun updateData(newStocks: List<Stock>) {
        val diffCallback = StockDiffUtilCallback(stockList, newStocks)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        stockList = newStocks.toMutableList()
        diffResult.dispatchUpdatesTo(this)
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