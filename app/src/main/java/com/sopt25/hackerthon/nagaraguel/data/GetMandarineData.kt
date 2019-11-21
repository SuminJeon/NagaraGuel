package com.sopt25.hackerthon.nagaraguel.data

import com.google.gson.annotations.SerializedName

data class GetMandarineData (
    @SerializedName("귤 받음")
    val today_mandarine : Int,
    @SerializedName("누적 귤")
    val mandarine_stack : Int
)