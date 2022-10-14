package com.android.vengateshm.androidpractice.airbnb.mavericks.feature

import com.airbnb.epoxy.TypedEpoxyController
import com.android.vengateshm.androidpractice.airbnb.mavericks.models.User
import com.android.vengateshm.androidpractice.userListItem

class UserListController : TypedEpoxyController<List<User>>() {
    override fun buildModels(data: List<User>?) {
        data ?: return

        if (data.isNullOrEmpty().not()) {
            data.forEachIndexed { index, user ->
                userListItem {
                    id(index.toLong())
                    user(user)
                }
            }
        }
    }
}