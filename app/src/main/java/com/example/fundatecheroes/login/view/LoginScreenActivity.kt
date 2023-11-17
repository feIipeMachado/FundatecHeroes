package com.example.fundatecheroes.login.view

import  android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.fundatecheroes.HomeActivity
import com.example.fundatecheroes.R
import com.example.fundatecheroes.databinding.ActivityLoginScreenBinding
import com.example.fundatecheroes.login.presentation.LoginViewModel
import com.example.fundatecheroes.login.presentation.model.LoginViewState
import com.example.fundatecheroes.profile.view.ProfileScreenActivity
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

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
                is LoginViewState.Success -> navegarTelaHome()
                is LoginViewState.Error -> SnackbarErroGeral()
                LoginViewState.Loading -> TODO()
                LoginViewState.ShowEmailError ->
                   SnackbarErroEmail()

                LoginViewState.ShowPasswordError ->
                    SnackbarErroSenha()
            }
        }
    }

    private fun botaoLogin() {
        binding.login.setOnClickListener {
            viewModel.validarInputs(
                email = binding.loginEmail.text.toString(),
                password = binding.loginSenha.text.toString()
            )
        }
    }

    private fun navegarTelaHome() {
        val intent = Intent(this@LoginScreenActivity, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun botaoCriarConta() {
        binding.criarConta.setOnClickListener {
            val intent = Intent(this@LoginScreenActivity, ProfileScreenActivity::class.java)
            startActivity(intent)
        }
    }

    private fun SnackbarErroEmail() {
        Snackbar.make(
            binding.root,
            R.string.email_invalido,
            BaseTransientBottomBar.LENGTH_LONG
        )
            .show()

    }

    private fun SnackbarErroSenha() {
        Snackbar.make(
            binding.root,
            R.string.senha_invalida,
            BaseTransientBottomBar.LENGTH_LONG
        )
            .show()
    }

    private fun SnackbarErroGeral() {
        Snackbar.make(
            binding.root,
            "Login ou senha inválidos",
            BaseTransientBottomBar.LENGTH_LONG
        )
            .show()
    }
}