package com.example.messagechat.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.messagechat.data.api.ApiService
import com.example.messagechat.data.api.IApiService
import com.example.messagechat.data.api.request.LoginRequest
import com.example.messagechat.data.api.response.LoginResponse
import com.example.messagechat.databindingcomponent.ObservableViewModel
import com.example.messagechat.utils.UtilMethods
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel : ObservableViewModel() {

    private var loginResponse = MutableLiveData<LoginResponse>().apply {
        value = null
    }

    fun login(email: String, password: String) {
        ApiService.getApiService().doLogin(LoginRequest(email, password))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ loginRes ->
                loginResponse.value = loginRes
            }, {error ->
                UtilMethods.hideLoading()
                Log.e("LoginService", "login error: $error")
            })
    }

    fun getLoginResponse() = loginResponse
}