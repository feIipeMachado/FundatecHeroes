package com.example.fundatecheroes.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.fundatecheroes.HomeActivity
import com.example.fundatecheroes.R
import com.example.fundatecheroes.databinding.ActivityLoginScreenBinding
import com.example.fundatecheroes.home.presentation.model.HomeViewState

class LoginScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        botaoLogin()
        botaoCriarConta()
    }

    private fun botaoLogin() {
        binding.login.setOnClickListener {
            val intent = Intent(this@LoginScreenActivity, HomeActivity::class.java)
            startActivity(intent)
        }

        val novoPorAqui = findViewById<Button>(R.id.novoPorAqui)

        novoPorAqui.setOnClickListener {
            val intent = Intent(this@LoginScreenActivity,ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun botaoCriarConta() {
        binding.criarConta.setOnClickListener {
            val intent = Intent(this@LoginScreenActivity, ProfileScreenActivity::class.java)
            startActivity(intent)
        }
    }



}