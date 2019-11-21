package com.pickcocast.loginandboard.network.request.register


import com.sopt25.hackerthon.nagaraguel.data.ResponseValidateAndRegisterAndLogin
import retrofit2.Call

interface RequestRegisterInterface {
    fun requestRegister(userID : String, userPassword:String, userPhone : String) : Call<ResponseValidateAndRegisterAndLogin>
}