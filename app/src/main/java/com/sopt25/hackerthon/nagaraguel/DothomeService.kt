package com.pickcocast.loginandboard.network



import com.sopt25.hackerthon.nagaraguel.data.ResponseValidateAndRegisterAndLogin
import retrofit2.Call
import retrofit2.http.*

interface DothomeService {
    //회원가입 화면의 중복확인 부분
    @FormUrlEncoded
    @POST("/users/check")
    fun getValidation(
        @Field("id")userID : String//userID에 String 타입 userID 값 전달.
    ) : Call<ResponseValidateAndRegisterAndLogin> //validate해서 받는 데이터의 형식.
    //반환 JSON객체 : {"success" : true}

    //패스워드 암호화 고려해서 MySQL의 행 길이를 더 길게 해줘야 함!!!★★★★★★★★★★★★★
    @FormUrlEncoded
    @POST("/users/signup")
    fun requestRegister(
        @Field("id") userID : String,
        @Field("pwd")userPassword : String,
        @Field("phone")userPhone : String
    ) : Call<ResponseValidateAndRegisterAndLogin>



    @FormUrlEncoded
    @POST("/users/signin")
    fun requestLogin(
        @Field("id") userID : String,
        @Field("pwd")userPassword : String
    ) : Call<ResponseValidateAndRegisterAndLogin>
}