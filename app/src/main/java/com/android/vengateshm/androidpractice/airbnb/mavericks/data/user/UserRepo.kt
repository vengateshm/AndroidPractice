package com.android.vengateshm.androidpractice.airbnb.mavericks.data.user

import com.android.vengateshm.androidpractice.airbnb.mavericks.models.User
import io.reactivex.Observable

interface UserRepo {
    fun getUserList(): Observable<List<User>>
}