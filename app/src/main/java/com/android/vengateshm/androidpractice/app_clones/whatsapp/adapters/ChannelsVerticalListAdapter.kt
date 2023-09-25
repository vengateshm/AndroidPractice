package com.android.vengateshm.androidpractice.app_clones.whatsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.android.vengateshm.androidpractice.app_clones.whatsapp.models.Channel
import com.android.vengateshm.androidpractice.databinding.VerticalChannelListItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class ChannelsVerticalListAdapter : RecyclerView.Adapter<ChannelsVerticalListAdapter.ViewHolder>() {
    var channelsList: List<Channel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = VerticalChannelListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return channelsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(channelsList[position])
    }

    inner class ViewHolder(private val binding: VerticalChannelListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(channel: Channel) {
            with(binding) {
                tvChannelName.text = channel.name
                if (channel.unreadCount > 0) {
                    tvUnreadCount.isVisible = true
                    tvDotSeparator.isVisible = true
                    tvUnreadCount.text = "\u2B24 ${channel.unreadCount} unread"
                    tvDotSeparator.text = "\u2022"
                } else {
                    tvUnreadCount.isVisible = false
                    tvDotSeparator.isVisible = false
                }
                tvTime.text = channel.lastContent?.time ?: ""
                tvContent.text = channel.lastContent?.content ?: ""
                Glide.with(ivProfilePic)
                    .load(channel.channelImageUrl)
                    .apply(RequestOptions().transform(CircleCrop()))
                    .into(ivProfilePic)
                channel.lastContent?.let { content ->
                    if (content.thumbUrl.isNotBlank()) {
                        Glide.with(ivContentPic)
                            .load(content.thumbUrl)
                            .apply(RequestOptions().transform(RoundedCorners(16)))
                            .into(ivContentPic)
                    }
                }
            }
        }
    }
}