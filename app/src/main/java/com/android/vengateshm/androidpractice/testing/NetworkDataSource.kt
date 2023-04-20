package com.android.vengateshm.androidpractice.testing

import com.android.vengateshm.androidpractice.kotlin_apis.extension_functions.Todo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NetworkDataSource {
    private fun getApi(): NetworkApi {
        return NetworkApi.create()
    }

    fun getTodo(): Single<List<Todo>> {
        return getApi().getTodos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getTodo(scheduler: ISchedulerProvider): Single<List<Todo>> {
        return getApi().getTodos()
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.main())
    }
}