package com.example.logindemo.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.logindemo.R
import com.example.logindemo.auth.AuthListener
import com.example.logindemo.databinding.ActivityMainBinding
import com.example.logindemo.viewmodel.AuthViewModel

class MainActivity : AppCompatActivity(), AuthListener {
    var login: LiveData<String>? = null
    var password: LiveData<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.authListener = this

        mContext = this.applicationContext
    }

    companion object {
        lateinit var mContext: Context
    }

    override fun onStarted() {
        Log.d("LoginActivity", "onStarted()")
    }

    override fun onSuccess(response: LiveData<String>) {
        response.observe(this, {
            Log.d("LoginActivity OnSuccess", response.toString())
            //then we would go to the main activity
        })
    }

    override fun failure(message: String) {
        Log.d("LoginActivity Failure", message)
    }
}