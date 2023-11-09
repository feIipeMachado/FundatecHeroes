package com.example.fundatecheroes.login.presentation

import LoginUseCase
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundatecheroes.login.presentation.model.LoginViewState
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val viewState: MutableLiveData<LoginViewState> = MutableLiveData()
    val state: LiveData<LoginViewState> = viewState

    private val useCase by lazy {
        LoginUseCase()
    }

    fun validarInputs(email: String?, password: String?) {
        if (email.isNullOrBlank()) {
            viewState.value = LoginViewState.ShowEmailError
            return
        }

        if (password.isNullOrBlank()) {
            viewState.value = LoginViewState.ShowPasswordError
            return
        }

        viewModelScope.launch {
            val isSuccess = useCase.loginUser(email, password)

            if (isSuccess) {
                viewState.value = LoginViewState.Success
            } else {
                viewState.value = LoginViewState.Error
            }
        }
    }
}
