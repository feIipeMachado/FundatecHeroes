package com.example.fundatecheroes.login.view

import  android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fundatecheroes.HomeActivity
import com.example.fundatecheroes.databinding.ActivityLoginScreenBinding
import com.example.fundatecheroes.profile.view.ProfileScreenActivity

class LoginScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        getSupportActionBar()?.hide()
        setContentView(binding.root)

        BotaoLogin()
        BotaoCriarConta()
    }

    private fun BotaoLogin() {
        binding.login.setOnClickListener {
            val intent = Intent(this@LoginScreenActivity, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun BotaoCriarConta() {
        binding.criarConta.setOnClickListener {
            val intent = Intent(this@LoginScreenActivity, ProfileScreenActivity::class.java)
            startActivity(intent)
        }
    }



}