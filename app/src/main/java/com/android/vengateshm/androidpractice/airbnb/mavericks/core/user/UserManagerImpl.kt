package com.android.vengateshm.androidpractice.airbnb.mavericks.core.user

import com.android.vengateshm.androidpractice.airbnb.mavericks.data.user.UserRepo
import com.android.vengateshm.androidpractice.airbnb.mavericks.models.User
import io.reactivex.Observable

class UserManagerImpl(private val userRepo: UserRepo) : UserManager {
    override fun getUserList(): Observable<List<User>> {
        return userRepo.getUserList()
    }
}