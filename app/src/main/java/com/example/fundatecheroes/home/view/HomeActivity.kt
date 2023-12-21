package com.example.fundatecheroes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.fundatecheroes.character_creation.view.CharacterCreationActivity
import com.example.fundatecheroes.databinding.ActivityHomeBinding
import com.example.fundatecheroes.home.presentation.HomeViewModel
import com.example.fundatecheroes.home.presentation.model.HomeViewState
import com.example.fundatecheroes.home.view.CharacterListAdapter
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private val adapter: CharacterListAdapter by lazy {
        CharacterListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.list.adapter = adapter

        botaoCriarPersonagem()
        swipeDeletarPersonagem()



        viewModel.state.observe(this){
            when (it) {
                is HomeViewState.Success -> adapter.addList(it.listaPersonagens)
                HomeViewState.EmptyList -> snackbarListaVazia()
                HomeViewState.Loading -> TODO()
                HomeViewState.DeleteCharacter -> snackbarDeletarPersonagem()
            }
        }

    }


    private fun swipeDeletarPersonagem() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(v: RecyclerView, h: RecyclerView.ViewHolder, t: RecyclerView.ViewHolder) = false
            override fun onSwiped(h: RecyclerView.ViewHolder, dir: Int) {
                val id = adapter.getIdAt(h.adapterPosition)
                viewModel.deletarPersonagem(id)
                adapter.removeAt(h.adapterPosition)
            }
        }).attachToRecyclerView(binding.list)
    }
    private fun botaoCriarPersonagem() {
        binding.criarPersonagem.setOnClickListener {
            val intent = Intent(this@HomeActivity, CharacterCreationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun snackbarListaVazia() {
        Snackbar.make(
            binding.root,
            R.string.lista_vazia,
            BaseTransientBottomBar.LENGTH_LONG
        )
            .show()

    }

    private fun snackbarDeletarPersonagem() {
        Snackbar.make(
            binding.root,
            R.string.deletar_personagem,
            BaseTransientBottomBar.LENGTH_LONG
        )
            .show()

    }




}


