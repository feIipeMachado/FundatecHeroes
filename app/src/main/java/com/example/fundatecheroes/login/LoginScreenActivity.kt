package com.example.fundatecheroes.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.fundatecheroes.HomeActivity
import com.example.fundatecheroes.R
import com.example.fundatecheroes.home.presentation.model.HomeViewState

class LoginScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        val login = findViewById<Button>(R.id.login)

        login.setOnClickListener {
            val intent = Intent(this@LoginScreenActivity, HomeActivity::class.java)
            startActivity(intent)
        }
    }



}