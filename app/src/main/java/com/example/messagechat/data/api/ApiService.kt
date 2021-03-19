package com.example.messagechat.data.api

import com.example.messagechat.utils.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object ApiService {

    // create separate api corresponds with api service such as apiUser - IUserApiService if there are too many services
    // here I created only one api using for all services defined in IApiService
    private val api: IApiService = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(ApiWorker.gsonConverter)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(ApiWorker.httpClient)
        .build()
        .create(IApiService::class.java)

//    private val api: IApiService by lazy { Retrofit.Builder()
//        .baseUrl(Constants.BASE_URL)
//        .addConverterFactory(ApiWorker.gsonConverter)
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//        .client(ApiWorker.httpClient)
//        .build()
//        .create(IApiService::class.java)  }

    fun getApiService() = api
}