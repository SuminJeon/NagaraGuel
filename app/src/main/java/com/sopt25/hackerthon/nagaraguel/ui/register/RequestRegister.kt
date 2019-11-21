package com.pickcocast.loginandboard.network.request.register


import com.pickcocast.loginandboard.network.DothomeServiceImpl
import com.sopt25.hackerthon.nagaraguel.data.ResponseValidateAndRegisterAndLogin
import retrofit2.Call

class RequestRegister() :
    RequestRegisterInterface {
    override fun requestRegister(
        userID: String,
        userPassword: String,
        userPhone: String
    ): Call<ResponseValidateAndRegisterAndLogin> {
        return DothomeServiceImpl.service.requestRegister(userID,userPassword,userPhone)
    }

}