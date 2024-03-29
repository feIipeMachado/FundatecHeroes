package com.example.fundatecheroes.profile.presentation

import LoginUseCase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundatecheroes.profile.presentation.model.ProfileViewState
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val viewState: MutableLiveData<ProfileViewState> = MutableLiveData()
    val state: LiveData<ProfileViewState> = viewState

    private val useCase by lazy{
        LoginUseCase()
    }

    fun validarInputs(name: String, email: String, password: String) {
        if (name.isNullOrEmpty()) {
            viewState.value = ProfileViewState.NameError
            return
        }

        if (!email.contains("@") && !email.contains(".com")) {
            viewState.value = ProfileViewState.EmailError
            return
        }

        if (password.length < 8) {
            viewState.value = ProfileViewState.PasswordError
            return
        }

        viewModelScope.launch {
            val isSuccess = useCase.createUser(
                name = name,
                email = email,
                password = password
            )

            if (isSuccess) {
                viewState.value = ProfileViewState.Success
            } else {
                viewState.value = ProfileViewState.Error
            }
        }
    }
}