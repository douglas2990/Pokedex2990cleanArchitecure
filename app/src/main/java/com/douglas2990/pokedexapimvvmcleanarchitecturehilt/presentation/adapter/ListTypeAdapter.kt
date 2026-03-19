package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon.Type
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.ListTypeAdapterBinding

class ListTypeAdapter(
    private val list: List<Type>,
    private var listenner: PokemonInterface? = null
) : RecyclerView.Adapter<ListTypeAdapter.ListTypeViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListTypeViewHolder {
        return ListTypeViewHolder(
            ListTypeAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListTypeViewHolder, position: Int) {
        val type = list[position]
        val typeName = type.type.name.lowercase()
        holder.buttonType.text = type.type.name.uppercase()

        // Mapeamento de cores por tipo de Pokémon
        val color = when (typeName) {
            "fire" -> "#F08030"
            "water" -> "#6890F0"
            "grass" -> "#78C850"
            "electric" -> "#F8D030"
            "ice" -> "#98D8D8"
            "fighting" -> "#C03028"
            "poison" -> "#A040A0"
            "ground" -> "#E0C068"
            "flying" -> "#A890F0"
            "psychic" -> "#F85888"
            "bug" -> "#A8B820"
            "rock" -> "#B8A038"
            "ghost" -> "#705898"
            "dragon" -> "#7038F8"
            "dark" -> "#705848"
            "steel" -> "#B8B8D0"
            "fairy" -> "#EE99AC"
            "normal" -> "#A8A878"
            else -> "#A9A978" // Normal ou desconhecido
        }

        holder.buttonType.backgroundTintList = ColorStateList.valueOf(Color.parseColor(color))
        holder.buttonType.setTextColor(Color.WHITE)
    }

    class ListTypeViewHolder(binding: ListTypeAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var buttonType = binding.btnType
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
