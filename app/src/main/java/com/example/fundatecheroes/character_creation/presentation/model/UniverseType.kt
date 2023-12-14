package com.example.fundatecheroes.character_creation.presentation.model

import java.lang.IllegalArgumentException

enum class UniverseType {
    MARVEL,
    DC;

    companion object {
        fun valueOf(position: Int): String {
            return when (position) {
                1 -> MARVEL.name
                2 -> DC.name
                else -> throw IllegalArgumentException("Posição invalida")
            }
        }
    }
}