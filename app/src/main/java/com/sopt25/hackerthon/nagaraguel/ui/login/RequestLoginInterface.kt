package com.pickcocast.loginandboard.network.request.login


import com.sopt25.hackerthon.nagaraguel.data.ResponseValidateAndRegisterAndLogin
import retrofit2.Call

interface RequestLoginInterface {
    fun requestLogin(userID : String, userPassword:String) : Call<ResponseValidateAndRegisterAndLogin>
}