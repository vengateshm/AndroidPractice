package com.android.vengateshm.androidpractice.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.vengateshm.androidpractice.databinding.MutualFundListItemBinding
import com.bumptech.glide.Glide

class MutualFundAdapter : RecyclerView.Adapter<MutualFundAdapter.ViewHolder>() {

    var currentSelectedReturnYr = ReturnYr.ONE
    var mutualFundList = mutableListOf<MutualFund>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            MutualFundListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mutualFundList[position]
        holder.bindItem(item)
    }

    override fun getItemCount(): Int {
        return mutualFundList.size
    }

    inner class ViewHolder(private val binding: MutualFundListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(mutualFund: MutualFund) {
            with(binding) {
                if (mutualFund.imageUrl.isNotBlank()) {
                    Glide.with(ivImage)
                        .load(mutualFund.imageUrl)
                        .into(ivImage)
                }
                tvName.text = mutualFund.name
                tvRiskTypeCap.text = "${mutualFund.risk} • ${mutualFund.type} • ${mutualFund.cap}"
                tvReturn.text = when (currentSelectedReturnYr) {
                    ReturnYr.ONE -> mutualFund.return1Yr
                    ReturnYr.THREE -> mutualFund.return2Yr
                    ReturnYr.FIVE -> mutualFund.return3Yr
                }
            }
        }
    }
}