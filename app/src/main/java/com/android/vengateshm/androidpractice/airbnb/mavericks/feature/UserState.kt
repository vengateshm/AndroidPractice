package com.android.vengateshm.androidpractice.airbnb.mavericks.feature

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.android.vengateshm.androidpractice.airbnb.mavericks.models.User

data class UserState(
    val asyncUserList: Async<List<User>> = Uninitialized,
    val userList: List<User> = mutableListOf(),
) : MavericksState
