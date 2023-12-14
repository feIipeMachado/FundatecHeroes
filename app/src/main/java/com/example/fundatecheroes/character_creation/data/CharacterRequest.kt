package com.example.fundatecheroes.character_creation.data

import java.time.LocalDateTime
import java.util.Date

data class CharacterRequest(
    val name: String,
    val description: String,
    val image: String,
    val universeType: String,
    val characterType: String,
    val age: Int,
    val birthday: Date?= null
    )