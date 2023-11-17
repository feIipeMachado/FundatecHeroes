package com.example.fundatecheroes.profile.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.viewModels
import com.example.fundatecheroes.HomeActivity
import com.example.fundatecheroes.R
import com.example.fundatecheroes.databinding.ActivityProfileScreenBinding
import com.example.fundatecheroes.login.presentation.LoginViewModel
import com.example.fundatecheroes.login.presentation.model.LoginViewState
import com.example.fundatecheroes.profile.presentation.ProfileViewModel
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar

class ProfileScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileScreenBinding
    private val viewModel: ProfileViewModel by viewModels();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileScreenBinding.inflate(layoutInflater)

        supportActionBar?.hide()
        setContentView(binding.root)

        viewModel.state.observe(this) {
            when (it) {
                is ProfileViewModel.Success -> navegarTelaHome()
                is LoginViewState.Error -> SnackbarErroGeral()
                LoginViewState.Loading -> TODO()
                LoginViewState.ShowEmailError ->
                    SnackbarErroEmail()

                LoginViewState.ShowPasswordError ->
                    SnackbarErroSenha()
            }
        }

    }

    private fun navegarHome() {
        val intent = Intent(this@ProfileScreenActivity, HomeActivity::class.java)
        startActivity(intent)
    }


    private fun snackbarSucesso() {
        Snackbar.make(
            binding.root,
            R.string.sucesso_criar_conta,
            LENGTH_LONG
        )
            .show()

        Handler(Looper.getMainLooper()).postDelayed({navegarHome()}, 2000L)

    }

    private fun snackbarNomeInvalido() {
        Snackbar.make(
            binding.root,
            R.string.nome_invalido,
            LENGTH_LONG
        )
            .show()

    }

    private fun snackbarEmailInvalido() {
        Snackbar.make(
            binding.root,
            R.string.email_invalido,
            LENGTH_LONG
        )
            .show()

    }

    private fun snackbarSenhaInvalida() {
        Snackbar.make(
            binding.root,
            R.string.senha_invalida,
            LENGTH_LONG
        )
            .show()
    }
}