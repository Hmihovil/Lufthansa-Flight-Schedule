package com.example.lufthansa_soft.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.example.lufthansa_soft.utils.Constants
import com.example.lufthansa_soft.MyApplication
import com.example.lufthansa_soft.R
import com.example.lufthansa_soft.utils.showSnackbar
import com.example.lufthansa_soft.ui.MainActivity
import com.example.lufthansa_soft.viewModel.AuthState
import com.example.lufthansa_soft.viewModel.SharedViewModel
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    val viewModel: SharedViewModel by viewModel()
//    val apiService: ApiService by inject()

//    lateinit var pref: SharedPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
//        pref = SharedPrefs(applicationContext)

        viewModel.getToken(
            Constants.CLIENT_ID,
            Constants.CLIENT_SECRET,
            Constants.GRANT_TYPE)

        viewModel.loading.observe(this, Observer {
            when(it) {
                is AuthState.Loading -> {
                    loading_token.visibility = View.VISIBLE
                }
                is AuthState.Success -> {
                    GlobalScope.launch {
                        delay(1000)
                        Log.e("token>>>>>", it.token)
                        MyApplication.pref.token = it.token
                        proceedToMainActivity()
                    }
                }
                is AuthState.Error -> {
                    loading_token.visibility = View.GONE
                    splash_wrapper.showSnackbar("Could not sign In")
                }
            }
        })
    }

    private fun proceedToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
