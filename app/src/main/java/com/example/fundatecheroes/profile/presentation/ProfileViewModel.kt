package com.example.fundatecheroes.profile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fundatecheroes.login.presentation.model.LoginViewState
import com.example.fundatecheroes.profile.presentation.model.ProfileViewState

class ProfileViewModel {

    private val viewState: MutableLiveData<ProfileViewState> = MutableLiveData()
    val state: LiveData<ProfileViewState> = viewState

    fun validarInputs(email: String, password: String) {
        if (!email.contains("@") && !email.contains(".com")) {
            viewState.value = ProfileViewState.ShowEmailError
        }

        if (password.length < 8) {
            viewState.value = ProfileViewState.ShowPasswordError
        }
    }
}