package com.android.vengateshm.androidpractice.app_clones.whatsapp.models

data class Chat(
    val id: Int,
    val toName: String,
    val toProfilePic: String,
    val lastMessage: ChatMessage,
) {
    companion object {
        fun getChats(): List<Chat> {
            return listOf(
                Chat(
                    id = 1,
                    toName = "John Doe",
                    toProfilePic = "https://images.unsplash.com/photo-1580518380430-2f84c0a7fb85?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=256&q=80",
                    lastMessage = ChatMessage(
                        id = 97,
                        status = MessageStatus.SENT,
                        content = "Hello",
                        type = MessageType.TEXT,
                        mediaUrl = "",
                        time = "2:14pm"
                    )
                ),
                Chat(
                    id = 2,
                    toName = "Jane Smith",
                    toProfilePic = "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=256&q=80",
                    lastMessage = ChatMessage(
                        id = 98,
                        status = MessageStatus.DELIVERED,
                        content = "Happy Republic Day!",
                        type = MessageType.IMAGE,
                        mediaUrl = "",
                        time = "Yesterday"
                    )
                ),
                Chat(
                    id = 3,
                    toName = "David Brown",
                    toProfilePic = "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=256&q=80",
                    lastMessage = ChatMessage(
                        id = 99,
                        status = MessageStatus.READ,
                        content = "Watch this!",
                        type = MessageType.VIDEO,
                        mediaUrl = "",
                        time = "20/09/23"
                    )
                ),
                Chat(
                    id = 4,
                    toName = "Sara Thompson",
                    toProfilePic = "https://images.unsplash.com/photo-1580489944761-15a19d654956?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=256&q=80",
                    lastMessage = ChatMessage(
                        id = 100,
                        status = MessageStatus.READ,
                        content = "",
                        type = MessageType.AUDIO,
                        mediaUrl = "",
                        time = "21/09/23"
                    )
                )
            )
        }
    }
}

data class ChatMessage(
    val id: Int,
    val status: MessageStatus,
    val content: String,
    val type: MessageType,
    val mediaUrl: String,
    val time: String,
)

enum class MessageStatus {
    SENT, DELIVERED, READ
}

enum class MessageType {
    TEXT, AUDIO, IMAGE, VIDEO
}