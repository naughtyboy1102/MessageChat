package com.example.messagechat.data.repository.login

import com.example.messagechat.data.remote.api.response.LoginResponse
import io.reactivex.Observable

interface LoginRepository {

    fun doLogin(email: String, password: String) : Observable<LoginResponse>

    /* This is another solution for getting data when retrofit receive response from server in repository class
    fun doLogin(email: String, password: String, loggedInCallback: LoggedInCallback)

    interface LoggedInCallback {
        fun loggedIn(loggedIn: Boolean)
    }
    */
}