package com.example.logindemo.viewmodel

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.logindemo.auth.AuthListener
import com.example.logindemo.repositories.UserRepository
import kotlinx.coroutines.Dispatchers

class AuthViewModel() : ViewModel(){

    var title: String = "Login"
    var email: String? = null
    var password: String? = null

    var authListener: AuthListener? = null


    fun onLoginButtonClick(view: View){
        Log.d("working", "...")
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            // failure
            Log.d("bad", "not working")
            authListener?.failure("failure")
            return
        }
        // success


        var loginResponse = UserRepository().login(email!!, password!!)
        Log.d("good", loginResponse.toString())
        authListener?.onSuccess(loginResponse)
    }

}