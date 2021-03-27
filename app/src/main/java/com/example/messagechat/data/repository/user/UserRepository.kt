package com.example.messagechat.data.repository.user

import com.example.messagechat.data.remote.api.response.UserResponse
import io.reactivex.Observable

interface UserRepository {
    fun getUserInfo(authToken: String, id: String) : Observable<UserResponse>
}