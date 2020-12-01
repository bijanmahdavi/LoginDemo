package com.example.logindemo.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.logindemo.helper.TokenManager
import com.example.logindemo.model.LoginResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    fun login(email:String, password: String): LiveData<String> {
        Log.d("UserRepository login", "Logging in ?")
        val loginResponse = MutableLiveData<String>()
        Log.d("UserRepository loginres", loginResponse.toString())
        MyApi().login(email, password)
            .enqueue(object: Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if(response.body() != null){
                        Log.d("suc_response" , response.toString())
                        loginResponse.value = "Login success"
                        var token = response.body()!!.token
                        var userId = response.body()!!.user._id
                        Log.d("login_response", response.body().toString())
                        TokenManager().storeToken(token, userId)
                        Log.d("Token", TokenManager().isLoggedIn().toString())
                        //start next activity
                    } else {
                        loginResponse.value = "Account not registered"
                        Log.d("User Repository", "This account does not exist")
                        Log.d("User Repository resp", response.toString())
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.d("fail_response" , t.message!!)
                    loginResponse.value = t.message
                }

            })

        return loginResponse
    }
}