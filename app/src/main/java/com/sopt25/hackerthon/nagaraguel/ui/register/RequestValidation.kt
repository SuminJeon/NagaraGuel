package com.pickcocast.loginandboard.network.request.validate


import com.pickcocast.loginandboard.network.DothomeServiceImpl
import com.sopt25.hackerthon.nagaraguel.data.ResponseValidateAndRegisterAndLogin
import retrofit2.Call

//요청하는 클래스.
class RequestValidation :
    RequestValidationInterface {
    override fun requestValidate(userID :String): Call<ResponseValidateAndRegisterAndLogin> {
        return DothomeServiceImpl.service.getValidation(userID)
    }
}