package com.example.fundatecheroes.login.data.repository

import com.example.fundatecheroes.login.data.LoginRequest
import com.example.fundatecheroes.login.data.remote.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("/api/login")
    suspend fun createUser(
        @Body loginRequest: LoginRequest,
    ): Response<LoginResponse>
}