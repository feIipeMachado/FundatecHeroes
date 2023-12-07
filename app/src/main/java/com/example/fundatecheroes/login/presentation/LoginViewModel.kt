package com.example.fundatecheroes.login.presentation

import LoginUseCase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundatecheroes.login.presentation.model.LoginViewState
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val viewState: MutableLiveData<LoginViewState> = MutableLiveData()
    val state: LiveData<LoginViewState> = viewState

    private val useCase by lazy {
        LoginUseCase()
    }

    fun validarInputs(email: String?, password: String?) {
        viewState.value = LoginViewState.Loading

        if (email.isNullOrBlank() || !email.contains("@") && !email.contains(".com")) {
            viewState.value = LoginViewState.EmailEmBranco
            return
        }

        if (password.isNullOrBlank()) {
            viewState.value = LoginViewState.SenhaEmBranco
            return
        }

        if (password.length < 8) {
            viewState.value = LoginViewState.ErroGeral
            return
        }

        viewModelScope.launch {
            val isSuccess = useCase.loginUser(email, password)

            if (isSuccess) {
                viewState.value = LoginViewState.Success
            } else {
                viewState.value = LoginViewState.ErroGeral
            }
        }
    }
}
