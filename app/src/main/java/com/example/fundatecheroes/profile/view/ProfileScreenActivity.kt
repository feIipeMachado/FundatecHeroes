package com.example.fundatecheroes.profile.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.ContactsContract.Profile
import android.util.Log
import androidx.activity.viewModels
import com.example.fundatecheroes.HomeActivity
import com.example.fundatecheroes.R
import com.example.fundatecheroes.databinding.ActivityProfileScreenBinding
import com.example.fundatecheroes.login.presentation.LoginViewModel
import com.example.fundatecheroes.login.presentation.model.LoginViewState
import com.example.fundatecheroes.login.view.LoginScreenActivity
import com.example.fundatecheroes.profile.presentation.ProfileViewModel
import com.example.fundatecheroes.profile.presentation.model.ProfileViewState
import com.google.android.material.snackbar.BaseTransientBottomBar
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

        botaoCriarConta()

        viewModel.state.observe(this) {
            when (it) {
                ProfileViewState.Success -> snackbarSucesso()
                ProfileViewState.Error -> snackbarErroGeral()
                ProfileViewState.Loading -> TODO()
                ProfileViewState.NameError ->
                    snackbarNomeInvalido()

                ProfileViewState.EmailError ->
                    snackbarEmailInvalido()

                ProfileViewState.PasswordError ->
                    snackbarSenhaInvalida()
            }
        }

    }

    private fun botaoCriarConta() {
        binding.criarConta.setOnClickListener {
            viewModel.validarInputs(
                name = binding.loginNome.text.toString(),
                email = binding.loginEmail.text.toString(),
                password = binding.loginSenha.text.toString()
            )
        }
    }

    private fun navegarTelaLogin() {
        finish()
    }


    private fun snackbarSucesso() {
        Snackbar.make(
            binding.root,
            R.string.sucesso_criar_conta,
            LENGTH_LONG
        )
            .show()

        Handler(Looper.getMainLooper()).postDelayed({ navegarTelaLogin() }, 300L)

    }

    private fun snackbarNomeInvalido() {
        Snackbar.make(
            binding.root,
            R.string.nome_invalido_profile,
            LENGTH_LONG
        )
            .show()

    }

    private fun snackbarEmailInvalido() {
        Snackbar.make(
            binding.root,
            R.string.email_invalido_profile,
            LENGTH_LONG
        )
            .show()

    }

    private fun snackbarSenhaInvalida() {
        Snackbar.make(
            binding.root,
            R.string.senha_invalida_profile,
            LENGTH_LONG
        )
            .show()
    }

    private fun snackbarErroGeral() {
        Snackbar.make(
            binding.root,
            R.string.erro_geral_profile,
            BaseTransientBottomBar.LENGTH_LONG
        )
            .show()
    }

}