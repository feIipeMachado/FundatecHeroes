package com.example.fundatecheroes.splash.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.fundatecheroes.R
import com.example.fundatecheroes.databinding.ActivityLoginScreenBinding
import com.example.fundatecheroes.databinding.ActivitySplashScreenBinding
import com.example.fundatecheroes.login.view.LoginScreenActivity

private val SPLASH_DISPLAY_LENGTH = 3000L

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        getSupportActionBar()?.hide()
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({ login() }, SPLASH_DISPLAY_LENGTH)
    }

    private fun login() {
        val intent = Intent(this@SplashScreenActivity, LoginScreenActivity::class.java)
        startActivity(intent)
        finish()
    }


}