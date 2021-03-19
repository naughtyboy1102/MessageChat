package com.example.messagechat.models

import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("_id")
    var userId: String = ""
    @SerializedName("name")
    var userName: String = ""
    @SerializedName("email")
    var userEmail: String = ""
    @SerializedName("avatar")
    var userAvatar: String? = ""
    @SerializedName("__v")
    var __v : Int = 0
}