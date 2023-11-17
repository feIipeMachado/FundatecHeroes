package com.example.fundatecheroes.profile.presentation.model

import com.example.fundatecheroes.login.presentation.model.LoginViewState

sealed class ProfileViewState {
    object Success : ProfileViewState()
    object Loading : ProfileViewState()
    object Error : ProfileViewState()
    object ShowNameError : ProfileViewState()
    object ShowEmailError : ProfileViewState()
    object ShowPasswordError : ProfileViewState()


}
