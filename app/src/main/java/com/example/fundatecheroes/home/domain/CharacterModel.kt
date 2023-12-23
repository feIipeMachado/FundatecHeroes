package com.example.fundatecheroes.home.domain

import com.example.fundatecheroes.character_creation.presentation.model.CharacterType
import java.io.Serializable

data class CharacterModel(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val age: Int,
    val universeType: String,
    val characterType: String,
): Serializable