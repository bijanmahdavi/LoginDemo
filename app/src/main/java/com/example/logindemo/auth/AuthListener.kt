package com.example.logindemo.auth

import androidx.lifecycle.LiveData

interface AuthListener {

    fun onStarted()
    fun onSuccess(response: LiveData<String>)
    fun failure(message: String)
}