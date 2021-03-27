package com.example.messagechat.data.repository.user

import android.app.Application
import com.example.messagechat.data.remote.api.ApiService
import com.example.messagechat.data.remote.api.response.UserResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserRepositoryImpl(private val app: Application) : UserRepository {
    override fun getUserInfo(authToken: String, id: String): Observable<UserResponse> {
        return ApiService.getApiService().getUserInfo(authToken, id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}