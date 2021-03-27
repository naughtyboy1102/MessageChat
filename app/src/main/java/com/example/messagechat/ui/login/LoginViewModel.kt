package com.example.messagechat.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.messagechat.data.remote.api.response.LoginResponse
import com.example.messagechat.data.repository.login.LoginRepository
import com.example.messagechat.databindingcomponent.ObservableViewModel
import com.example.messagechat.utils.UtilMethods
import io.reactivex.disposables.CompositeDisposable

class LoginViewModel(private val loginRepository: LoginRepository) : ObservableViewModel() {

    private var loginResponse = MutableLiveData<LoginResponse>()
    private var compositeDisposable = CompositeDisposable()

    fun doLogin(email: String, password: String) {
        compositeDisposable.add(
            loginRepository.doLogin(email, password)
            .subscribe({ loginRes ->
                loginResponse.value = loginRes
            }, { error ->
                UtilMethods.hideLoading()
                Log.e("LoginService", "login error: $error")
            })
        )
    }

    fun getLoginResponse() = loginResponse

    fun getDisposable() = compositeDisposable

    /* This is another solution for getting data when retrofit receive response from server in repository class
    private fun loggedInCallback() = object : LoginRepository.LoggedInCallback {
        override fun loggedIn(loggedIn: Boolean) {
            isLoggedIn.value = loggedIn
        }
    }
    private var isLoggedIn = MutableLiveData<Boolean>()

    fun doLogin(email: String, password: String) {
        loginRepository.doLogin(email, password, loggedInCallback())
    }
    fun getIsLoggedIn() = isLoggedIn
    */
}