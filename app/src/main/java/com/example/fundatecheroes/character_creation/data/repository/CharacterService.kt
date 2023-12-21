package com.example.fundatecheroes.character_creation.data.repository

import com.example.fundatecheroes.character_creation.data.CharacterRequest
import com.example.fundatecheroes.character_creation.data.remote.CharacterResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {

    @POST("/api/character/{id}")
    suspend fun createCharacter(
        @Path("id") id: Int,
        @Body characterRequest: CharacterRequest
    ): Response<ResponseBody>

    @GET("/api/character/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int
    ): Response<List<CharacterResponse>>

    @DELETE("/api/character/{id}")
    suspend fun deleteCharaccter(
        @Path("id") id: Int
    ): Response<ResponseBody>
}