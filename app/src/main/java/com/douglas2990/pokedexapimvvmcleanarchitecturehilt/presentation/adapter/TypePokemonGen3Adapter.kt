package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.ListTypeAdapterBinding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.gen_iii.TypeGen3

class TypePokemonGen3Adapter(
    private val types: List<TypeGen3>
) : RecyclerView.Adapter<TypePokemonGen3Adapter.TypeViewHolder>() {

    class TypeViewHolder(val binding: ListTypeAdapterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeViewHolder {
        val binding = ListTypeAdapterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TypeViewHolder, position: Int) {
        val type = types[position]
        val typeName = type.name.lowercase()
        holder.binding.btnType.text = typeName.uppercase()

        // Mapeamento de cores oficial (Geração III / Hoenn Style)
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
            "normal" -> "#A8A878"
            "fairy" -> "#EE99AC" 
            else -> "#A8A878"
        }

        holder.binding.btnType.backgroundTintList = ColorStateList.valueOf(Color.parseColor(color))
        holder.binding.btnType.setTextColor(Color.WHITE)
    }

    override fun getItemCount(): Int = types.size
}
