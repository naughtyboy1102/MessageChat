package com.example.messagechat.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.messagechat.data.local.models.User
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

@Dao
interface UserDAO {

    @Query("select * from user")
    fun getUser(): Observable<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User): Completable
}