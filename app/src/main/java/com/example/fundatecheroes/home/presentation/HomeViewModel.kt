package com.example.fundatecheroes.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundatecheroes.character_creation.domain.CharacterUseCase
import com.example.fundatecheroes.home.domain.CharacterModel
import com.example.fundatecheroes.home.presentation.model.HomeViewState
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val viewState: MutableLiveData<HomeViewState> = MutableLiveData()
    val state: LiveData<HomeViewState> = viewState

    private val useCase by lazy {
        CharacterUseCase()
    }

    private fun listarPersonagens() {
        viewModelScope.launch {
            validarLista(useCase.getCharacter())
        }

    }

    private fun validarLista(lista: List<CharacterModel>) {
        if (lista.isEmpty()) {
            viewState.value = HomeViewState.EmptyList
        } else {
            viewState.value = HomeViewState.Success(lista)
        }
    }

    fun deletarPersonagem(id: Int) {
        viewModelScope.launch {
            useCase.deleteCharacter(id)
            viewState.value = HomeViewState.DeleteCharacter
        }
    }

    init {
        listarPersonagens()
    }

}