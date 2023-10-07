package com.example.fundatecheroes.login.view

import  android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.fundatecheroes.HomeActivity
import com.example.fundatecheroes.databinding.ActivityLoginScreenBinding
import com.example.fundatecheroes.login.view.presentation.LoginViewModel
import com.example.fundatecheroes.login.view.presentation.model.LoginViewState
import com.example.fundatecheroes.profile.view.ProfileScreenActivity

class LoginScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginScreenBinding
    private val viewModel: LoginViewModel by viewModels();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        getSupportActionBar()?.hide()
        setContentView(binding.root)

        botaoLogin()
        botaoCriarConta()

        viewModel.state.observe(this) {
            when (it) {
                is LoginViewState.Success -> TODO()
                is LoginViewState.Error -> TODO()
                LoginViewState.Loading -> TODO()
                LoginViewState.ShowEmailError ->
                    mostrarErroEmail()
                LoginViewState.ShowPasswordError ->
                    mostrarErroSenha()
            }
        }
    }

    private fun botaoLogin() {
        binding.login.setOnClickListener {
            val intent = Intent(this@LoginScreenActivity, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun botaoCriarConta() {
        binding.criarConta.setOnClickListener {
            val intent = Intent(this@LoginScreenActivity, ProfileScreenActivity::class.java)
            startActivity(intent)
        }
    }

    private fun mostrarErroEmail() {
        binding.loginEmail.error = "digite um email válido"
    }

    private fun mostrarErroSenha() {
        binding.loginSenha.error = "digite uma senha válido"
    }
}