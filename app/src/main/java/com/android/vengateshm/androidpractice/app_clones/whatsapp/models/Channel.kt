package com.android.vengateshm.androidpractice.app_clones.whatsapp.models

data class Channel(
    val id: Int,
    val name: String,
    val channelImageUrl: String,
    val unreadCount: Int = 0,
    val time: String = "",
    val lastContent: ChannelContent? = null,
) {
    companion object {
        fun getFindChannelList(): List<Channel> {
            return listOf(
                Channel(
                    id = 3,
                    name = "Star Sports",
                    channelImageUrl = "https://www.vhv.rs/dpng/d/444-4440151_logopedia-star-sports-logo-png-transparent-png.png"
                ),
                Channel(
                    id = 4,
                    name = "CNBC-TV18",
                    channelImageUrl = "https://seeklogo.com/images/C/cnbc-tv18-logo-B6335AA0FB-seeklogo.com.png"
                ),
                Channel(
                    id = 5,
                    name = "ICC",
                    channelImageUrl = "https://resources.pulse.icc-cricket.com/ICC/photo/2018/04/05/58a0d39a-d5f7-496d-b7bf-9fca17dfa130/73068.jpg"
                )
            )
        }

        fun getChannelList(): List<Channel> {
            return listOf(
                Channel(
                    id = 1,
                    name = "moneycontrol",
                    channelImageUrl = "https://static.wikia.nocookie.net/logopedia/images/e/e2/MC.jpeg/revision/latest/scale-to-width-down/170?cb=20200423190138",
                    unreadCount = 171,
                    lastContent = ChannelContent(
                        id = 1,
                        content = "Pakistani cyber attackers using country domain code to target Indian defense personnel. \n" +
                                "\n" +
                                "Read the full story for details ⬇️",
                        type = ChannelContentType.IMAGE,
                        mediaUrl = "",
                        thumbUrl = "https://media.istockphoto.com/id/1127637966/photo/computing-and-malware-concept.jpg?s=612x612&w=0&k=20&c=bFVqATf4Z_MjlPOiTO4hvKHyJm2o02Bw5ZvNYfAT2d4=",
                        time = "18 minutes ago",
                    )
                ),
                Channel(
                    id = 2,
                    name = "Amazon miniTV",
                    channelImageUrl = "https://yt3.googleusercontent.com/fBNVJ-Ywc2Kp8Ph-0EvCxP-JKk1aFAs-8Hbh0SCbwegM5_0ErzZkSyQVZEhxxk5xbubUwcnOMaA=s176-c-k-c0x00ffffff-no-rj",
                    lastContent = ChannelContent(
                        id = 2,
                        content = "New thriller web series launched. Watch the trailer!",
                        type = ChannelContentType.VIDEO,
                        mediaUrl = "",
                        thumbUrl = "https://blogger.googleusercontent.com/img/a/AVvXsEhqTXv8Kbl0aX9Qnzz2IdVxK6EMQct9B3XKLV67jEad6LixC9Zq3CEppKxyMl0C_viOc362Zd4XFKQvABjHv1iOnjtlGxCwmQYIFfKiYtrUkwBjHMoCT5qTj0Qp9kjJjbDlzMyoLe5ETL8KZTbObid4TitsZpQDNEOgbTFqKHxpXo144WRTKq0myXA4=s16000",
                        time = "Yesterday",
                    )
                )
            )
        }
    }
}

data class ChannelContent(
    val id: Int,
    val content: String,
    val type: ChannelContentType,
    val mediaUrl: String,
    val thumbUrl: String,
    val time: String,
)

enum class ChannelContentType {
    LINK, GIF, IMAGE, VIDEO
}