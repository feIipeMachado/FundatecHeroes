package com.example.fundatecheroes.character_creation.data.repository

import android.util.Log
import com.example.fundatecheroes.character_creation.data.CharacterRequest
import com.example.fundatecheroes.character_creation.data.remote.CharacterResponse
import com.example.fundatecheroes.character_creation.domain.CharacterUseCase
import com.example.fundatecheroes.database.FundatecHeroesDatabase
import com.example.fundatecheroes.home.domain.CharacterModel
import com.example.fundatecheroes.login.data.LoginRequest
import com.example.fundatecheroes.network.RetrofitNetworkClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.DELETE
import java.lang.Exception
import java.util.Date

class CharacterRepository {

    private val repository = RetrofitNetworkClient.createNetworkClient(
        baseUrl = "https://fundatec.herokuapp.com"
    ).create(CharacterService::class.java)

    private val database by lazy {
        FundatecHeroesDatabase.getInstance().userDao()
    }

    suspend fun createCharacter(
        name: String,
        description: String,
        image: String,
        universeType: String,
        characterType: String,
        age: Int,
        birthday: Date? = null
    ): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val response = repository.createCharacter(
                    id = database.getUserId(),
                    characterRequest = CharacterRequest(
                        name = name,
                        description = description,
                        image = image,
                        universeType = universeType,
                        characterType = characterType,
                        age = age,
                        birthday = null
                    )
                )
                response.isSuccessful
            } catch (ex: Exception) {
                Log.e("createCharacter", ex.message.toString())
                false
            }
        }
    }

    suspend fun getCharacter(): List<CharacterModel> {
        return withContext(Dispatchers.IO) {
            try {
                val response = repository.getCharacter(
                    id = database.getUserId()
                )
                response.body()?.toModel() ?: listOf()
            } catch (ex: Exception) {
                Log.e("getCharacter", ex.message.toString())
                listOf()
            }
        }
    }

    suspend fun deleteCharacter(id: Int): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val response = repository.deleteCharaccter(
                    id = id,
                )
                response.isSuccessful
            } catch (ex: Exception) {
                Log.e("deleteCharacter", ex.message.toString())
                false
            }
        }
    }

    private fun List<CharacterResponse>.toModel(): List<CharacterModel> {
        return map { characterResponse ->
            CharacterModel(
                id = characterResponse.id,
                name =  characterResponse.name,
                image = characterResponse.image
            )
        }
    }


}


