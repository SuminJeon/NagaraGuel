package com.sopt25.hackerthon.nagaraguel.data

import com.google.gson.annotations.SerializedName

data class GetMandarineData (
    @SerializedName("success")
    val success : Boolean,
    @SerializedName("data")
    val mandarine_stack : Int
)