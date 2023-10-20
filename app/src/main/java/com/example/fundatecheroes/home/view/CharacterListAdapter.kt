package com.example.fundatecheroes.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fundatecheroes.databinding.CharacterItemListBinding
import com.example.fundatecheroes.home.domain.CharacterModel


class CharacterListAdapter : RecyclerView.Adapter<CharacterViewHolder>() {
    private val list: MutableList<CharacterModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = CharacterItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addList(items:List<CharacterModel>) {
        list.addAll(items)
        notifyDataSetChanged()
    }

    fun removeItem() {
        val position: Int = list.size
        list.removeAt(position)
        notifyItemChanged(position)
    }
}