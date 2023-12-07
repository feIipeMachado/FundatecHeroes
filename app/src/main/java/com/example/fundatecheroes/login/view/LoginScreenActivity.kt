package com.example.fundatecheroes.login.view

import  android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.example.fundatecheroes.HomeActivity
import com.example.fundatecheroes.R
import com.example.fundatecheroes.databinding.ActivityLoginScreenBinding
import com.example.fundatecheroes.gone
import com.example.fundatecheroes.login.presentation.LoginViewModel
import com.example.fundatecheroes.login.presentation.model.LoginViewState
import com.example.fundatecheroes.profile.view.ProfileScreenActivity
import com.example.fundatecheroes.visible
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay

class LoginScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginScreenBinding
    private val viewModel: LoginViewModel by viewModels();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        botaoLogin()
        botaoCriarConta()

        viewModel.state.observe(this) {
            when (it) {
                LoginViewState.Success -> snackbarSucesso()
                LoginViewState.ErroGeral -> snackbarErroGeral()
                LoginViewState.Loading -> binding.carregando.visible()
                LoginViewState.EmailEmBranco ->
                   snackbarEmailEmBranco()

                LoginViewState.SenhaEmBranco ->
                    snackbarSenhaEmBranco()
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
        finish()
    }

    private fun snackbarSucesso() {
        Snackbar.make(
            binding.root,
            "Login feito com sucesso",
            BaseTransientBottomBar.LENGTH_LONG
        )
            .show()
        binding.carregando.gone()

        Handler(Looper.getMainLooper()).postDelayed({navegarTelaHome()}, 300L)

    }

    private fun botaoCriarConta() {
        binding.criarConta.setOnClickListener {
            val intent = Intent(this@LoginScreenActivity, ProfileScreenActivity::class.java)
            startActivity(intent)
        }
    }

    private fun snackbarEmailEmBranco() {
        Snackbar.make(
            binding.root,
            R.string.email_embranco_login,
            BaseTransientBottomBar.LENGTH_LONG
        )
            .show()
        binding.carregando.gone()


    }

    private fun snackbarSenhaEmBranco() {
        Snackbar.make(
            binding.root,
            R.string.senha_embranco_login,
            BaseTransientBottomBar.LENGTH_LONG
        )
            .show()
        binding.carregando.gone()

    }

    private fun snackbarErroGeral() {
        Snackbar.make(
            binding.root,
            R.string.erro_geral_login,
            BaseTransientBottomBar.LENGTH_LONG
        )
            .show()
        binding.carregando.gone()

    }


}