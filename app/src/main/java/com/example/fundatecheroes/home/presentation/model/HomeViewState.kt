package com.example.fundatecheroes.home.presentation.model

import com.example.fundatecheroes.home.domain.CharacterModel

sealed class HomeViewState {
    data class Success(val listaPersonagens: List<CharacterModel>) : HomeViewState()
    object Loading : HomeViewState()
    object Error : HomeViewState()
}