package com.example.messagechat.data.api.response

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("message") val message : String,
    @SerializedName("id") val id : String,
    @SerializedName("email") val email : String,
    @SerializedName("token") val token : String
)