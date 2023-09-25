package com.android.vengateshm.androidpractice.app_clones.whatsapp.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.vengateshm.androidpractice.app_clones.whatsapp.models.Chat
import com.android.vengateshm.androidpractice.app_clones.whatsapp.models.ChatMessage
import com.android.vengateshm.androidpractice.app_clones.whatsapp.models.MessageStatus
import com.android.vengateshm.androidpractice.app_clones.whatsapp.models.MessageType
import com.android.vengateshm.androidpractice.databinding.ChatListItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions

class ChatListAdapter : RecyclerView.Adapter<ChatListAdapter.ViewHolder>() {

    var chatList: List<Chat> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ChatListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(chatList[position])
    }

    inner class ViewHolder(private val binding: ChatListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(chat: Chat) {
            with(binding) {
                tvTo.text = chat.toName
                tvTime.text = chat.lastMessage.time
                Glide.with(ivProfilePic)
                    .load(chat.toProfilePic)
                    .apply(RequestOptions().transform(CircleCrop()))
                    .into(ivProfilePic)
                if (chat.hasStatus) {
                    vStatusBackground.visibility = View.VISIBLE
                } else {
                    vStatusBackground.visibility = View.INVISIBLE
                }
                with(chat.lastMessage) {
                    when (status) {
                        MessageStatus.SENT -> {
                            tvTick.text = "\u2713"
                            tvTick.setTextColor(Color.parseColor("#777777"))
                        }

                        MessageStatus.DELIVERED -> {
                            tvTick.text = "✓✓"
                            tvTick.setTextColor(Color.parseColor("#777777"))
                        }

                        MessageStatus.READ -> {
                            tvTick.text = "✓✓"
                            tvTick.setTextColor(Color.parseColor("#6495ED"))
                        }
                    }
                    tvLastMessageContent.text = getFormattedMessage(this)
                }
            }
        }
    }
}

fun getFormattedMessage(message: ChatMessage): String {
    var msg = ""
    when (message.type) {
        MessageType.IMAGE -> {
            msg = "\uD83D\uDCF7  "
        }

        MessageType.VIDEO -> {
            msg = "\uD83C\uDFA5  "
        }

        MessageType.AUDIO -> {
            msg = "\uD83C\uDF99️  "
        }

        else -> {
            msg = ""
        }
    }
    msg += message.content
    return msg
}