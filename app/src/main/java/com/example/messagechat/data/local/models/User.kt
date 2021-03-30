package com.example.messagechat.data.local.models

import androidx.lifecycle.Transformations.map
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.messagechat.data.remote.api.response.UserResponse

@Entity
data class User (
    @PrimaryKey
    val id: String,
    val name: String,
    val email: String,
    val avatar: String)

    fun User.asResponseModel() : UserResponse {
        return UserResponse(
                userId = id,
                userName = name,
                userEmail = email,
                userAvatar = avatar
            )
    }