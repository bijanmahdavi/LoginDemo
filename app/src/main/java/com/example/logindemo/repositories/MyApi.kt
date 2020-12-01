package com.example.logindemo.repositories

import com.example.logindemo.model.LoginResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface MyApi {


    @FormUrlEncoded
    @POST("auth/login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    companion object{
        operator fun invoke(): MyApi {
            return Retrofit.Builder()
                .baseUrl("https://apolis-property-management.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}