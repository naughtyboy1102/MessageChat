package com.example.messagechat.data.api

import com.example.messagechat.data.api.request.LoginRequest
import com.example.messagechat.data.api.response.LoginResponse
import com.example.messagechat.data.api.response.UserResponse
import io.reactivex.Observable
import retrofit2.http.*

interface IApiService {
    // get user by email
    @GET("/users/{email}")
    fun getUserInfo(
        @Header("Authorization") authToken: String,
        @Path("email") email: String
    ): Observable<UserResponse>

    // login
    @POST("/accounts/login")
    fun doLogin(
        @Body loginPostData: LoginRequest
    ) : Observable<LoginResponse>

}