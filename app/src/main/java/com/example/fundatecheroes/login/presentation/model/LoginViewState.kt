package com.example.fundatecheroes.login.presentation.model

sealed class LoginViewState {
    data class Success(val message: String) : LoginViewState()
    object Loading : LoginViewState()
    data class Error(val errorMessage: String) : LoginViewState()
    object ShowEmailError : LoginViewState()
    object ShowPasswordError : LoginViewState()
}