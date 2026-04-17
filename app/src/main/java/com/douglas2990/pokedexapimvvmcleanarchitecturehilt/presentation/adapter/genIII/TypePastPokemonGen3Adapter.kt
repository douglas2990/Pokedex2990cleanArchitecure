package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.genIII

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.R
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon2.TypeSlotDTO
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.ItemTypePokemonGen3Binding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.ListTypeAdapterBinding
// Importe o TypeSlotDTO do seu pacote detailPokemon2
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.gen_iii.PastTypeDTO
import kotlin.text.lowercase
import kotlin.text.uppercase



class TypePastPokemonGen3Adapter(private val types: List<TypeSlotDTO>) :
    RecyclerView.Adapter<TypePastPokemonGen3Adapter.TypeViewHolder>() {

    class TypeViewHolder(val binding: ItemTypePokemonGen3Binding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeViewHolder {
        val binding = ItemTypePokemonGen3Binding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TypeViewHolder, position: Int) {
        val typeSlot = types[position]
        var typeName = typeSlot.type.name.lowercase()

        // LÓGICA DE TRATAMENTO: Se for Fada na Gen 3, mostramos como Normal
        if (typeName == "fairy") {
            typeName = "normal"
        }

        holder.binding.textType.text = typeName.uppercase()

        // Ajuste das cores conforme o tipo (usando o nome tratado)
        val context = holder.itemView.context
        val colorRes = when (typeName) {
            "normal" -> R.color.type_normal
            "fire" -> R.color.type_fire
            "water" -> R.color.type_water
            "grass" -> R.color.type_grass
            "electric" -> R.color.type_electric
            "ice" -> R.color.type_ice
            "fighting" -> R.color.type_fighting
            "poison" -> R.color.type_poison
            "ground" -> R.color.type_ground
            "flying" -> R.color.type_flying
            "psychic" -> R.color.type_psychic
            "bug" -> R.color.type_bug
            "rock" -> R.color.type_rock
            "ghost" -> R.color.type_ghost
            "dragon" -> R.color.type_dragon
            "steel" -> R.color.type_steel
            "dark" -> R.color.type_dark
            else -> R.color.type_normal
        }

        holder.binding.cardTypeGen3.setCardBackgroundColor(
            ContextCompat.getColor(context, colorRes)
        )
    }

    override fun getItemCount(): Int = types.size
}
