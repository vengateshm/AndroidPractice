package com.android.vengateshm.androidpractice.airbnb.mavericks.feature

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.android.vengateshm.androidpractice.airbnb.mavericks.core.user.UserManager
import com.android.vengateshm.androidpractice.airbnb.mavericks.core.user.UserManagerImpl
import com.android.vengateshm.androidpractice.airbnb.mavericks.data.user.UserRepoImpl

class UserViewModel(
    private val userManager: UserManager,
    userState: UserState,
) : BaseMvRxViewModel<UserState>(userState) {

    companion object : MavericksViewModelFactory<UserViewModel, UserState> {
        override fun create(viewModelContext: ViewModelContext, state: UserState): UserViewModel? {
            val userRepo = UserRepoImpl() // Can use DI
            val userManager = UserManagerImpl(userRepo) // Can use DI
            return UserViewModel(userManager, state)
        }
    }

    fun getUserList() {
        asyncSubscribe(
            UserState::asyncUserList,
            onSuccess = { list ->
                setState {
                    copy(userList = list)
                }
            },
            onFail = {

            })

        userManager.getUserList().execute {
            copy(asyncUserList = it)
        }
    }
}