package com.example.messagechat.ui.appmain

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import com.example.messagechat.data.api.ApiService
import com.example.messagechat.data.api.response.UserResponse
import com.example.messagechat.databindingcomponent.ObservableViewModel
import com.example.messagechat.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppMainViewModel : ObservableViewModel() {
    private var user = MutableLiveData<UserResponse>()
    private var userName = "No User"
    private var userAvatar: Drawable? = null
    private var userEmail : String = "null@gmail.com"
    //private lateinit var user : User

    fun fetchUserInfo() {
        ApiService.getApiService().getUserInfo(Constants.AUTH_TOKEN,"tung123@gmail.com")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ userResponse ->
                user.value = userResponse
            }, {error ->
                Log.e("UserService", "getUserInfo() error: $error")
            })
//        disposable.add(
//            userService.getUserInfo()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(object : DisposableSingleObserver<List<User>>() {
//                    override fun onSuccess(value: List<User>?) {
//                        user.value = value
//                        Log.d("UserService", "getUserInfo() success: $value")
//                    }
//                    override fun onError(e: Throwable?) {
//                        Log.e("UserService", "getUserInfo() error: $e")
//                    }
//                })
//        )
    }

    fun getUser() : MutableLiveData<UserResponse> = user

    fun getUserName(): String = userName

    fun getUserAvatar(): Drawable? = userAvatar

    fun getUserEmail(): String = userEmail

    fun setUserName(name: String) {
        userName = name
        //notifyPropertyChanged(BR.userName)
    }

    fun setUserAvatar(avatar: Drawable?) {
        userAvatar = avatar
        //notifyPropertyChanged(BR.userAvatar)
    }

    fun setUserEmail(name: String) {
        userEmail = name
        //notifyPropertyChanged(BR.userEmail)
    }
}