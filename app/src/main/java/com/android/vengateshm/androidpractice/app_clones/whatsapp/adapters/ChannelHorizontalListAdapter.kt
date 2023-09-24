package com.android.vengateshm.androidpractice.app_clones.whatsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.vengateshm.androidpractice.app_clones.whatsapp.models.Channel
import com.android.vengateshm.androidpractice.databinding.HorizontalChannelListItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions

class ChannelHorizontalListAdapter :
    RecyclerView.Adapter<ChannelHorizontalListAdapter.ViewHolder>() {

    var channelList: List<Channel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HorizontalChannelListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return channelList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(channelList[position])
    }

    inner class ViewHolder(private val binding: HorizontalChannelListItemBinding) :
        RecyclerView.ViewHolder(
            binding
                .root
        ) {

        fun bindItem(channel: Channel) {
            with(binding) {
                tvUserName.text = channel.name
                Glide.with(ivChannelPic)
                    .load(channel.channelImageUrl)
                    .apply(RequestOptions().transform(CircleCrop()))
                    .into(ivChannelPic)
            }
        }
    }
}