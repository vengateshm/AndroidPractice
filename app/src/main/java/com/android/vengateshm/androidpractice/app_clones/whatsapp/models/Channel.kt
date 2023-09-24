package com.android.vengateshm.androidpractice.app_clones.whatsapp.models

data class Channel(
    val id: Int,
    val name: String,
    val channelImageUrl: String,
) {
    companion object {
        fun getChannelList(): List<Channel> {
            return listOf(
                Channel(
                    id = 1,
                    name = "Star Sports",
                    channelImageUrl = "https://www.vhv.rs/dpng/d/444-4440151_logopedia-star-sports-logo-png-transparent-png.png"
                ),
                Channel(
                    id = 2,
                    name = "Aaj Tak",
                    channelImageUrl = "https://png2.cleanpng.com/sh/8a5391beff9e03b15a692d3b19083f59/L0KzQYm3VcE2N5tnj5H0aYP2gLBuTfFicl55ed02aX7nebK0lPVtbadui9t4bj3meLL1jvVtNZ1ujtt3Zz3wdbXwgb02aZdneaYEOEK3c7XsUb41OGg7TKU7NkG4Qoe7VMEyOWM2TaIDLoDxd1==/kisspng-aaj-tak-india-television-channel-living-media-5afba49824cde1.4076432615264411121508.png"
                ),
                Channel(
                    id = 3,
                    name = "ICC",
                    channelImageUrl = "https://resources.pulse.icc-cricket.com/ICC/photo/2018/04/05/58a0d39a-d5f7-496d-b7bf-9fca17dfa130/73068.jpg"
                )
            )
        }
    }
}
