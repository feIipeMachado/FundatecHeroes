package com.example.fundatecheroes.login.presentation.model

sealed class LoginViewState {
    object Success : LoginViewState()
    object Loading : LoginViewState()
    object ErroGeral : LoginViewState()
    object EmailEmBranco : LoginViewState()
    object SenhaEmBranco : LoginViewState()
}