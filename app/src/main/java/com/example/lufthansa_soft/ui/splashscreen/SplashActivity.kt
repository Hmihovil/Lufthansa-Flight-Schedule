package com.example.lufthansa_soft.ui.splashscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.lufthansa_soft.Constants.CLIENT_ID
import com.example.lufthansa_soft.Constants.CLIENT_SECRET
import com.example.lufthansa_soft.Constants.GRANT_TYPE
import com.example.lufthansa_soft.R
import com.example.lufthansa_soft.viewModel.AuthState
import com.example.lufthansa_soft.viewModel.SharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    val viewModel: SharedViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewModel.loading.observe(this, Observer {
            when(it) {
                is AuthState.Loading -> {

                }
                is AuthState.Success -> {

                }
                is AuthState.Error -> {

                }
            }
        })
    }
}
