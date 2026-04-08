package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.ListPokemonAdapterTypeDetailBinding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.detalhe.DetalhePokemonPython

class PokemonAdapterPython(
    private var list: List<DetalhePokemonPython>,
    private val context: Context,
    private var listener: PokemonInterface? = null
) : RecyclerView.Adapter<PokemonAdapterPython.PokemonViewHolder>() {

    fun updateList(newList: List<DetalhePokemonPython>) {
        list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = ListPokemonAdapterTypeDetailBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PokemonViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = list[position]
        val namePokemon = pokemon.nome
        val pokemonId = pokemon.id.toString()

        val pokemonNameFormatted = namePokemon.replaceFirstChar { it.titlecase() }
        val pokemonNumberFormatted = pokemonId.padStart(3, '0')

        holder.binding.textView.text = "#$pokemonNumberFormatted $pokemonNameFormatted"

        holder.binding.firstConstraint.setOnClickListener {
            listener?.onClick(pokemonId)
        }

        holder.binding.imageView.load(pokemon.esprites.other?.home?.front_default)

        holder.binding.recyclerView.layoutManager = GridLayoutManager(
            context.applicationContext,
            if (pokemon.tipos.isNotEmpty()) pokemon.tipos.size else 1
        )
        holder.binding.recyclerView.adapter = ListTypeAdapter(pokemon.tipos)
    }

    override fun getItemCount(): Int = list.size

    class PokemonViewHolder(val binding: ListPokemonAdapterTypeDetailBinding) :
        RecyclerView.ViewHolder(binding.root)
}
