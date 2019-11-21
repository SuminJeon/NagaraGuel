package com.pickcocast.loginandboard.network


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//DothomeService 인터페이스를 실체화 하였음. 어디서든 호출할 수 있다(싱글톤)
object DothomeServiceImpl{
    private const val BASE_URL = "http://ec2-13-125-42-117.ap-northeast-2.compute.amazonaws.com:3001"

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service : DothomeService = retrofit.create(DothomeService::class.java)
}