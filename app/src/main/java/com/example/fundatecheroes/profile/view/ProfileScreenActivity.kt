package com.example.fundatecheroes.profile.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.fundatecheroes.HomeActivity
import com.example.fundatecheroes.R
import com.example.fundatecheroes.databinding.ActivityProfileScreenBinding
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar

class ProfileScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileScreenBinding.inflate(layoutInflater)

        supportActionBar?.hide()
        setContentView(binding.root)

        val nome = binding.loginNome.toString()
        val email = binding.loginEmail.toString()
        val senha = binding.loginSenha.toString()

        binding.criarConta.setOnClickListener {
            if (!validarNome(nome)) {
                snackbarNomeInvalido()
            } else if (!validarEmail(email)) {
                snackbarEmailInvalido()
            } else if (!validarSenha(senha)) {
                snackbarSenhaInvalida()
            } else {
                snackbarSucesso()
            }
        }
    }

    private fun navegarHome() {
        val intent = Intent(this@ProfileScreenActivity, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun validarNome(nome: String): Boolean {
        return nome.isNotEmpty()
    }

    private fun validarEmail(email: String): Boolean {
        return email.contains("@") && email.contains(".com")
    }

    private fun validarSenha(senha: String): Boolean {
        return senha.length >=8
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