package com.example.messagechat.data.repository.login

import android.app.Application
import com.example.messagechat.data.remote.api.ApiService
import com.example.messagechat.data.remote.api.request.LoginRequest
import com.example.messagechat.data.remote.api.response.LoginResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginRepositoryImpl(private val app: Application) : LoginRepository {

    override fun doLogin(email: String, password: String): Observable<LoginResponse> {
        return ApiService.getApiService().doLogin(LoginRequest(email, password))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /* This is another solution for getting data when retrofit receive response from server in repository class
    override fun doLogin(email: String, password: String, loggedInCallback: LoginRepository.LoggedInCallback) {
        ApiService.getApiService().doLogin(LoginRequest(email, password))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ loginRes ->
                val pref = app.getSharedPreferences("LOGIN_ACCESS_TOKEN", Context.MODE_PRIVATE);
                val loginPref = pref.edit()
                loginPref.putString("accountId", loginRes.id)
                loginPref.putString("accountToken", loginRes.token)
                loginPref.putString("accountEmail", loginRes.email)
                loginPref.apply()
                loggedInCallback.loggedIn(true)
            }, { error ->
                UtilMethods.hideLoading()
                Log.e("LoginService", "login error: $error")
            })
    }
    */
}