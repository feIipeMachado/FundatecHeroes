package com.example.fundatecheroes.character_creation.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.InputType
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.example.fundatecheroes.HomeActivity
import com.example.fundatecheroes.R
import com.example.fundatecheroes.character_creation.presentation.CharacterViewModel
import com.example.fundatecheroes.character_creation.presentation.model.CharacterViewState
import com.example.fundatecheroes.databinding.ActivityCharacterCreationScreenBinding
import com.example.fundatecheroes.gone
import com.example.fundatecheroes.visible
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar

class CharacterCreationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCharacterCreationScreenBinding
    private lateinit var picker: DatePickerDialog
    private var heroiOuVilao = arrayOf(R.array.heroi_ou_vilao)
    private var marvelOuDc = arrayOf(R.array.marvel_ou_dc)
    private val viewModel: CharacterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterCreationScreenBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        val spinnerHeroiOuVilao = binding.heroOrVillain

        ArrayAdapter.createFromResource(
            this,
            R.array.heroi_ou_vilao,
            android.R.layout.simple_spinner_item
        )
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerHeroiOuVilao.adapter = adapter
            }


        ArrayAdapter.createFromResource(
            this,
            R.array.marvel_ou_dc,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.marvelOrDc.adapter = adapter
        }

        botaoCriarPersonagem()

        viewModel.state.observe(this) {
            when (it) {
                CharacterViewState.Success -> snackbarSucesso()
                CharacterViewState.Error -> snackbarErroGeral()
                CharacterViewState.Loading -> binding.carregandoCriarPersonagem.visible()
                CharacterViewState.EmptyFieldError -> snackbarCampoVazio()
                CharacterViewState.AgeError -> snackbarIdadeInvalidal()
            }
        }
    }


    private fun botaoCriarPersonagem() {
        binding.criarPersonagem.setOnClickListener {
            viewModel.validarInputs(
                name = binding.characterName.text.toString(),
                description = binding.characterDescription.text.toString(),
                image = binding.characterUrl.text.toString(),
                universeType = binding.marvelOrDc.selectedItemPosition,
                characterType = binding.heroOrVillain.selectedItemPosition,
                age = binding.age.text.toString().toInt()
            )
        }
    }

    private fun snackbarCampoVazio() {
        Snackbar.make(
            binding.root,
            R.string.campo_vazio,
            BaseTransientBottomBar.LENGTH_LONG
        )
            .show()

    }

    private fun snackbarErroGeral() {
        Snackbar.make(
            binding.root,
            R.string.erro_geral_character,
            BaseTransientBottomBar.LENGTH_LONG
        )
            .show()
        binding.carregandoCriarPersonagem.gone()


    }

    private fun snackbarIdadeInvalidal() {
        Snackbar.make(
            binding.root,
            R.string.idade_invalida,
            BaseTransientBottomBar.LENGTH_LONG
        )
            .show()

    }


    private fun snackbarSucesso() {
        Snackbar.make(
            binding.root,
            R.string.personagem_criado,
            BaseTransientBottomBar.LENGTH_LONG
        )
            .show()
        binding.carregandoCriarPersonagem.gone()

        Handler(Looper.getMainLooper()).postDelayed({ navegarHome() }, 2000L)

    }

    private fun navegarHome() {
        val intent = Intent(this@CharacterCreationActivity, HomeActivity::class.java)
        startActivity(intent)
    }


}