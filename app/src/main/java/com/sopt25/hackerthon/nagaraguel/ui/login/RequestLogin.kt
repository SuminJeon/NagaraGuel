package com.pickcocast.loginandboard.network.request.login

import com.pickcocast.loginandboard.network.DothomeServiceImpl
import com.sopt25.hackerthon.nagaraguel.data.ResponseValidateAndRegisterAndLogin
import retrofit2.Call

class RequestLogin() : RequestLoginInterface {

    override fun requestLogin(
        userID: String,
        userPassword: String
    ): Call<ResponseValidateAndRegisterAndLogin> {
        return DothomeServiceImpl.service.requestLogin(userID,userPassword)
    }

}