package com.android.vengateshm.androidpractice.airbnb.mavericks.core.user

import com.android.vengateshm.androidpractice.airbnb.mavericks.models.User
import io.reactivex.Observable

interface UserManager {
    fun getUserList(): Observable<List<User>>
}