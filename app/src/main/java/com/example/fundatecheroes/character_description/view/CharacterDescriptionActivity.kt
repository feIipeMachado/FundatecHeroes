package com.example.fundatecheroes.character_description.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.fundatecheroes.R
import com.example.fundatecheroes.character_creation.presentation.model.CharacterType
import com.example.fundatecheroes.databinding.ActivityCharacterDescriptionBinding
import com.example.fundatecheroes.home.domain.CharacterModel

class CharacterDescriptionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCharacterDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterDescriptionBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        val character = intent.extras?.getSerializable("character") as? CharacterModel
        if (character == null) {
            finish()
            return
        }
        Glide.with(binding.root.context).load(character.image).into(binding.imagemPersonagem)
        binding.nomeImagemPersonagem.text = character.name
        binding.descricaoPersonagem.text = character.description
        binding.idade.text = "Idade: " + character.age.toString()

        val heroiOuVilao = character.characterType
        if (heroiOuVilao == "HERO") {
            binding.heroiOuVilao.text = "Herói"
        } else {
            binding.heroiOuVilao.text = "Vilão"
        }

        val marvelDc = character.universeType.toString()
        if (marvelDc == "DC") {
            Glide.with(binding.root.context).load(R.drawable.logodc).into(binding.marvelDc)
        } else {
            Glide.with(binding.root.context).load(R.drawable.marvellogo).into(binding.marvelDc)
        }

    }


}