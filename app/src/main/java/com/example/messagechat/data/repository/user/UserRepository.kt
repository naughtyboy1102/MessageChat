package com.example.messagechat.data.repository.user

import com.example.messagechat.data.local.models.User
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

interface UserRepository {
    fun fetchUserInfoFromServer(authToken: String, id: String): Disposable
    //fun getUserInfo() : LiveData<User>
    fun getUserInfo() : Observable<User>

}