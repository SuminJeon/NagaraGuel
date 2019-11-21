package com.sopt25.hackerthon.nagaraguel.data

import com.google.gson.annotations.SerializedName

data class ResponseValidateAndRegisterAndLogin(
    @SerializedName("id")
    val userID : String,
    @SerializedName("success")
    val success : Boolean
)