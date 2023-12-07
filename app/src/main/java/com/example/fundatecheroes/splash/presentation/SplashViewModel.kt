package com.example.fundatecheroes.splash.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundatecheroes.splash.domain.IsValidCacheUseCase
import com.example.fundatecheroes.splash.presentation.model.SplashViewState
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    private val useCase by lazy {
        IsValidCacheUseCase()
    }

    private val viewState: MutableLiveData<SplashViewState> = MutableLiveData()
    val state: LiveData<SplashViewState> = viewState

    private fun validateUserCache() {
        viewModelScope.launch {
            if (useCase.isValidCache()) {
                viewState.value = SplashViewState.showHome
            } else {
                viewState.value = SplashViewState.showLogin
            }
        }
    }

    init {
        validateUserCache()
    }
}