package com.android.vengateshm.androidpractice.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.vengateshm.androidpractice.databinding.SettingsListItemBinding

class SettingsListAdapter(private val settingsList: MutableList<SettingItem>) :
    RecyclerView.Adapter<SettingsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            SettingsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(settingsList[position])
    }

    override fun getItemCount(): Int {
        return settingsList.size
    }

    fun onItemMove(fromPosition: Int, toPosition: Int) {
        val movedItem = settingsList.removeAt(fromPosition)
        settingsList.add(toPosition, movedItem)
        notifyItemMoved(fromPosition, toPosition)
    }

    inner class ViewHolder(private val binding: SettingsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(settingItem: SettingItem) {
            binding.tvSetting.text = settingItem.title
        }
    }
}