package com.example.fundatecheroes.character_creation.data

import com.squareup.moshi.JsonClass
import java.time.LocalDateTime
import java.util.Date

@JsonClass(generateAdapter = true)
data class CharacterRequest(
    val name: String,
    val description: String,
    val image: String,
    val universeType: String,
    val characterType: String,
    val age: Int,
    val birthday: String? = null
)