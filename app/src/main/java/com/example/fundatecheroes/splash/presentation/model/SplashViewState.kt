package com.example.fundatecheroes.splash.presentation.model

sealed class SplashViewState {
    object showLogin : SplashViewState()
    object showHome : SplashViewState()
}