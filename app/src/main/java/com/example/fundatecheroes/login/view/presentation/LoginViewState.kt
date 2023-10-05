package com.example.fundatecheroes.login.view.presentation

sealed class LoginViewState {
    data class email(val message: String) : LoginViewState()
    data class senha(val errorMessage: String) : LoginViewState()
}