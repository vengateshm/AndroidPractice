package com.android.vengateshm.androidpractice.app_clones.whatsapp.models

data class Status(
    val id: Int,
    val userName: String,
    val profilePicUrl: String,
    val statusContents: List<StatusContent>,
) {
    companion object {
        fun getStatusList(): List<Status> {
            return listOf(
                Status(
                    id = 1,
                    userName = "My Status",
                    profilePicUrl = "https://images.unsplash.com/photo-1534614971-6be99a7a3ffd?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=256&q=80",
                    statusContents = emptyList()
                ),
                Status(
                    id = 2,
                    userName = "Emily",
                    profilePicUrl = "https://images.unsplash.com/photo-1554151228-14d9def656e4?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=256&q=80",
                    statusContents = listOf(
                        StatusContent(
                            id = 1,
                            type = StatusContentType.IMAGE,
                            contentUrl = ""
                        ),
                        StatusContent(
                            id = 2,
                            type = StatusContentType.VIDEO,
                            contentUrl = ""
                        ),
                    )
                ),
                Status(
                    id = 3,
                    userName = "David",
                    profilePicUrl = "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=256&q=80",
                    statusContents = listOf(
                        StatusContent(
                            id = 3,
                            type = StatusContentType.TEXT,
                            contentUrl = ""
                        ),
                        StatusContent(
                            id = 4,
                            type = StatusContentType.IMAGE,
                            contentUrl = ""
                        ),
                    )
                ),
            )
        }
    }
}

