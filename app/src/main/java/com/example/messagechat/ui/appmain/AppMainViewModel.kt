package com.example.messagechat.ui.appmain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.messagechat.data.local.models.User
import com.example.messagechat.data.local.models.asResponseModel
import com.example.messagechat.data.remote.api.response.UserResponse
import com.example.messagechat.data.repository.user.UserRepository
import com.example.messagechat.databindingcomponent.ObservableViewModel
import com.example.messagechat.utils.UtilMethods
import io.reactivex.disposables.CompositeDisposable

class AppMainViewModel(private val userRepository: UserRepository) : ObservableViewModel() {
    private var user = MutableLiveData<UserResponse>()
    private var isLoggedIn = MutableLiveData<Boolean>()
    // This used to dispose observer when no need to use any more to avoid memory leaks problem, should be called in onDestroy
    // CompositeDisposable is used for containing multi observers, Disposable for single observer
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun fetchUserInfoFromServer(authToken: String, id: String) {
        compositeDisposable.add(
            userRepository.fetchUserInfoFromServer(authToken, id)
        )
    }

    fun getUserInfoFromDatabase() {
        compositeDisposable.add(
            userRepository.getUserInfo()
                .subscribe({ userResponse ->
                    user.value = userResponse.asResponseModel()
                    UtilMethods.hideLoading()
                }, { error ->
                    Log.e("AppMain", "getUserInfo() error: $error")
                })
        )
    }

    fun getUser() : MutableLiveData<UserResponse> = user

    fun getIsLoggedIn() : MutableLiveData<Boolean> = isLoggedIn

    fun getCompositeDisposable() = compositeDisposable

    fun setIsLoggedIn(value: Boolean) {
        isLoggedIn.value = value
    }
}