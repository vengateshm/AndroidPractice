package com.android.vengateshm.androidpractice.app_clones.whatsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.vengateshm.androidpractice.app_clones.whatsapp.models.Status
import com.android.vengateshm.androidpractice.app_clones.whatsapp.models.toBorderedData
import com.android.vengateshm.androidpractice.databinding.StatusListItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions

class StatusAdapter : RecyclerView.Adapter<StatusAdapter.ViewHolder>() {

    var statusList: List<Status> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            StatusListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return statusList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(statusList[position])
    }

    inner class ViewHolder(private val binding: StatusListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(status: Status) {
            with(binding) {
                tvUserName.text = status.userName

                if (status.statusContents.isEmpty()) {
                    vProfilePicBg.visibility = View.INVISIBLE
                } else {
                    vProfilePicBg.visibility = View.VISIBLE
                    vProfilePicBg.setSegments(status.statusContents.map { it.toBorderedData() })
                }

                Glide.with(ivProfilePic)
                    .load(status.profilePicUrl)
                    .apply(RequestOptions().transform(CircleCrop()))
                    .into(ivProfilePic)
            }
        }
    }
}