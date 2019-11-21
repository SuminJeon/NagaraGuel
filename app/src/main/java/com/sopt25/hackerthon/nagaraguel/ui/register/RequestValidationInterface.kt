package com.pickcocast.loginandboard.network.request.validate


import com.sopt25.hackerthon.nagaraguel.data.ResponseValidateAndRegisterAndLogin
import retrofit2.Call

interface RequestValidationInterface {
    fun requestValidate(userID : String) : Call<ResponseValidateAndRegisterAndLogin>
}