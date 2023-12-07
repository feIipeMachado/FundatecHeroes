package com.example.fundatecheroes.splash.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.example.fundatecheroes.HomeActivity
import com.example.fundatecheroes.R
import com.example.fundatecheroes.databinding.ActivityLoginScreenBinding
import com.example.fundatecheroes.databinding.ActivitySplashScreenBinding
import com.example.fundatecheroes.login.view.LoginScreenActivity
import com.example.fundatecheroes.splash.presentation.SplashViewModel
import com.example.fundatecheroes.splash.presentation.model.SplashViewState

private val SPLASH_DISPLAY_LENGTH = 3000L

class SplashScreenActivity : AppCompatActivity() {

    private val viewmodel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        getSupportActionBar()?.hide()
        viewmodel.state.observe(this) {
            when (it) {
                SplashViewState.showLogin -> mostrarTelaLogin()
                SplashViewState.showHome -> mostrarTelaHome()
            }
        }

        Handler(Looper.getMainLooper()).postDelayed({ login() }, SPLASH_DISPLAY_LENGTH)
    }

    private fun login() {
        val intent = Intent(this@SplashScreenActivity, LoginScreenActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun mostrarTelaLogin() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashScreenActivity, LoginScreenActivity::class.java))
            finish()
        }, SPLASH_DISPLAY_LENGTH)
    }

    private fun mostrarTelaHome() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashScreenActivity, HomeActivity::class.java))
            finish()
        }, SPLASH_DISPLAY_LENGTH)
    }
}

