package com.example.messagechat.data.api.response

import com.google.gson.annotations.SerializedName

data class UserResponse (
    @SerializedName("_id")
    var userId: String = "",
    @SerializedName("name")
    var userName: String = "",
    @SerializedName("email")
    var userEmail: String = "",
    @SerializedName("avatar")
    var userAvatar: String? = "",
    @SerializedName("__v")
    var __v : Int = 0
)