package com.example.fundatecheroes.character_creation.domain

import com.example.fundatecheroes.character_creation.data.remote.CharacterResponse
import com.example.fundatecheroes.character_creation.data.repository.CharacterRepository
import com.example.fundatecheroes.home.domain.CharacterModel
import java.util.Date

class CharacterUseCase {
    private val repository by lazy {
        CharacterRepository()
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
        return repository.createCharacter(
            name = name,
            description = description,
            image = image,
            universeType = universeType,
            characterType = characterType,
            age = age,
            birthday = birthday
        )
    }

    suspend fun getCharacter(): List<CharacterModel> {
        return repository.getCharacter()
    }

    suspend fun deleteCharacter(id: Int): Boolean {
        return repository.deleteCharacter(id = id)
    }
}