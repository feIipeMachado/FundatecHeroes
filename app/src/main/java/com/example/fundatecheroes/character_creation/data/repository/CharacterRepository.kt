package com.example.fundatecheroes.character_creation.data.repository

import android.util.Log
import com.example.fundatecheroes.character_creation.data.CharacterRequest
import com.example.fundatecheroes.login.data.LoginRequest
import com.example.fundatecheroes.network.RetrofitNetworkClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.Date

class CharacterRepository {

    private val repository = RetrofitNetworkClient.createNetworkClient(
        baseUrl = "https://fundatec.herokuapp.com"
    ).create(CharacterService::class.java)

    suspend fun createCharacter(
        name: String,
        description: String,
        image: String,
        universeType: String,
        characterType: String,
        age: Int,
        birthday: Date?= null
    ): Boolean {
        return withContext(Dispatchers.IO){
            try {
                val response = repository.createCharacter(
                    characterRequest = CharacterRequest(
                        name = name,
                        description = description,
                        image = image,
                        universeType = universeType,
                        characterType = characterType,
                        age = age,
                        birthday = birthday
                    )
                )
                response.isSuccessful
            } catch (ex: Exception) {
                Log.e("createCharacter", ex.message.toString())
                false
            }
        }
    }

}