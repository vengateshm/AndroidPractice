package com.android.vengateshm.androidpractice.airbnb.mavericks.data.user

import com.android.vengateshm.androidpractice.airbnb.mavericks.models.User
import io.reactivex.Observable

class UserRepoImpl : UserRepo {
    override fun getUserList(): Observable<List<User>> {
        return Observable.just(listOf(
            User("Edwin", "Hubble"),
            User("James", "Webber"),
            User("Edwin", "Hubble"),
            User("James", "Webber"),
            User("Edwin", "Hubble"),
            User("James", "Webber"),
            User("Edwin", "Hubble"),
            User("James", "Webber"),
            User("Edwin", "Hubble"),
            User("James", "Webber"),
            User("Edwin", "Hubble"),
            User("James", "Webber"),
            User("Edwin", "Hubble"),
            User("James", "Webber"),
        ))
    }
}