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
import com.example.fundatecheroes.databinding.ActivityCharacterCreationScreenBinding
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

        ArrayAdapter.createFromResource(this, R.array.heroi_ou_vilao, android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerHeroiOuVilao.adapter = adapter
            }

        val spinnerMarvelOuDc = binding.marvelOrDc

        ArrayAdapter.createFromResource(this, R.array.marvel_ou_dc, android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerMarvelOuDc.adapter = adapter
            }


        binding.dateOfBirth.setOnClickListener{
            selecionarDataNascimento()
        }

        binding.criarPersonagem.setOnClickListener{
            val nome = binding.characterName.text.toString()
            val descricao = binding.characterDescription.text.toString()
            val url = binding.characterUrl.text.toString()
            if (validarNome(nome)) {
                snackbarNomeInvalido()
            } else if (validarDescricao(descricao)) {
                snackbarDescricaoInvalida()
            } else if (validarUrl(url)) {
                snackbarUrlInvalida()
            } else {
                snackbarSucesso()
            }

        }

    }

    private fun selecionarDataNascimento() {
        val calendar: Calendar = Calendar.getInstance()
        val dia: Int = calendar.get(Calendar.DAY_OF_MONTH)
        val mes: Int = calendar.get(Calendar.MONTH)
        val ano: Int = calendar.get(Calendar.YEAR)
        val dateOfBirth = binding.dateOfBirth

        picker = DatePickerDialog(
            this@CharacterCreationActivity,
            DatePickerDialog.OnDateSetListener() { datePicker: DatePicker, _: Int, _: Int, _: Int ->
                fun onDateSet(view: DatePicker, ano: Int, mes: Int, dia: Int) {
                    dateOfBirth.setText("$dia/$mes/$ano")
                }
            }, dia, mes, ano
        )
        picker.show()
    }

    fun onItemSelected(parent: AdapterView<*>?,
                                view: View, position: Int,
                                id: Long) {
        Toast.makeText(applicationContext,
            heroiOuVilao[position],
            Toast.LENGTH_LONG)
            .show()
    }

    private fun validarNome(nome: String): Boolean {
        return nome.isNullOrBlank()
    }

    private fun validarDescricao(descricao: String): Boolean {
        return descricao.isNullOrBlank()
    }

    private fun validarUrl(url: String): Boolean {
        return url.isNullOrBlank()
    }

    private fun snackbarNomeInvalido() {
        Snackbar.make(
            binding.root,
            R.string.nome_vazio,
            BaseTransientBottomBar.LENGTH_LONG
        )
            .show()

    }

    private fun snackbarDescricaoInvalida() {
        Snackbar.make(
            binding.root,
            R.string.descricao_vazia,
            BaseTransientBottomBar.LENGTH_LONG
        )
            .show()

    }

    private fun snackbarUrlInvalida() {
        Snackbar.make(
            binding.root,
            R.string.url_vazia,
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

        Handler(Looper.getMainLooper()).postDelayed({navegarHome()}, 2000L)

    }

    private fun navegarHome() {
        val intent = Intent(this@CharacterCreationActivity, HomeActivity::class.java)
        startActivity(intent)
    }


}