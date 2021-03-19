package com.example.messagechat.data.services

import com.example.messagechat.data.api.IApiService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class UserService {

    private val BASE_URL = "http://192.168.1.97:3000" //"http://localhost:3000"
    private val api: IApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(IApiService::class.java)

//    init {
//        api = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .build()
//            .create(IChatApiService::class.java)
//    }

//    fun getUserInfo(): Single<User> {
//        return api.getUserInfo()
//    }
}