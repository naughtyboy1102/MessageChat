package com.example.messagechat.data.remote.api

import com.example.messagechat.data.remote.api.request.LoginRequest
import com.example.messagechat.data.remote.api.response.LoginResponse
import com.example.messagechat.data.remote.api.response.UserResponse
import io.reactivex.Observable
import retrofit2.http.*

interface IApiService {
    // get user by email
    @GET("/users/{id}")
    fun getUserInfo(
        @Header("Authorization") authToken: String,
        @Path("id") id: String
    ): Observable<UserResponse>

    // login
    @POST("/accounts/login")
    fun doLogin(
        @Body loginPostData: LoginRequest
    ) : Observable<LoginResponse>

}