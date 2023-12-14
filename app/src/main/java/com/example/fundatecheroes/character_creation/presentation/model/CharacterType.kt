package com.example.fundatecheroes.character_creation.presentation.model

import java.lang.IllegalArgumentException

enum class CharacterType {
    HERO,
    VILLAIN;

    companion object {
        fun valueOf(position: Int): String {
            return when (position) {
                1 -> HERO.name
                2 -> VILLAIN.name
                else -> throw IllegalArgumentException("Posição invalida")
            }
        }
    }
}