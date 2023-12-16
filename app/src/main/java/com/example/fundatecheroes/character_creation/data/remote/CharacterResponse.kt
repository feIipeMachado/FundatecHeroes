package com.example.fundatecheroes.character_creation.data.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterResponse(
    val id: Int,
    val name: String,
    val description: String,
    val image: String,
    val universeType: String,
    val characterType: String,
    val age: Int,
    val birthday: String? = null
)