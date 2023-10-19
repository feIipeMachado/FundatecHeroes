package com.example.fundatecheroes.home.view

import androidx.recyclerview.widget.RecyclerView
import com.example.fundatecheroes.databinding.CharacterItemListBinding
import com.example.fundatecheroes.home.domain.CharacterModel

class CharacterViewHolder(
    private val binding: CharacterItemListBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(character: CharacterModel) {
        binding.batman.text = character.name
    }
}