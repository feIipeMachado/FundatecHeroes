package com.example.fundatecheroes.character_creation.presentation.model

sealed class CharacterViewState {
    object Success : CharacterViewState()
    object Loading : CharacterViewState()
    object EmptyFieldError : CharacterViewState()
    object Error : CharacterViewState()
    object AgeError : CharacterViewState()

}