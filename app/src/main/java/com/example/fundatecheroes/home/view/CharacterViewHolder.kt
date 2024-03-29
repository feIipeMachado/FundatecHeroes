package com.example.fundatecheroes.home.view

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fundatecheroes.databinding.CharacterItemListBinding
import com.example.fundatecheroes.home.domain.CharacterModel

class CharacterViewHolder(
    private val binding: CharacterItemListBinding,
    private val listener: (CharacterModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(character: CharacterModel) {
        Glide.with(binding.root.context)
            .load(character.image)
            .into(binding.ivCharacter)
        binding.descricao.text = character.name

        binding.constraintLayout.setOnClickListener{
            listener(character)
        }
    }
}