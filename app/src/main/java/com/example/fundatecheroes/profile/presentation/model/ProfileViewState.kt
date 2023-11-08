package com.example.fundatecheroes.profile.presentation.model

import com.example.fundatecheroes.login.presentation.model.LoginViewState

sealed class ProfileViewState {
    data class Success(val message: String) : ProfileViewState()
    object Loading : ProfileViewState()
    data class Error(val errorMessage: String) : ProfileViewState()
    object ShowNameError : ProfileViewState()
    object ShowEmailError : ProfileViewState()
    object ShowPasswordError : ProfileViewState()


}
