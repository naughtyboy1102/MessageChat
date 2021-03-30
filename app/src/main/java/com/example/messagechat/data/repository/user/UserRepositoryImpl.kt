package com.example.messagechat.data.repository.user

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.messagechat.data.local.database.AppDatabase
import com.example.messagechat.data.local.models.User
import com.example.messagechat.data.local.models.asResponseModel
import com.example.messagechat.data.remote.api.ApiService
import com.example.messagechat.data.remote.api.response.UserResponse
import com.example.messagechat.data.remote.api.response.asDatabaseModel
import com.example.messagechat.utils.UtilMethods
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UserRepositoryImpl(app: Application) : UserRepository {
    private val database: AppDatabase = AppDatabase.getDatabase(app)

    //private val user = database.userDAO.getUser()
    override fun fetchUserInfoFromServer(authToken: String, id: String): Disposable {
        return ApiService.getApiService().getUserInfo(authToken, id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ userResponse ->
                insertToDB(userResponse)
            }, { error ->
                Log.e("UserService", "getUserInfo() error: $error")
                UtilMethods.hideLoading()
            })
    }

    //    override fun getUserInfo(): LiveData<User> {
////        return database.userDAO.getUser()
////    }
    @SuppressLint("CheckResult")
    private fun insertToDB(userResponse: UserResponse){
        database.userDAO.insertUser(userResponse.asDatabaseModel())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            }, { error ->
                Log.e("UserService", "getUserInfo() error: $error")
            })
    }

    override fun getUserInfo(): Observable<User> {
        return database.userDAO.getUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}