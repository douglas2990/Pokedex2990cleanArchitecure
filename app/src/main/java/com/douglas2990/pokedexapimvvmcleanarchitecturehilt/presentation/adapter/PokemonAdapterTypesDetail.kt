package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.ListPokemonAdapterTypeDetailBinding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.detalhe.DetalhePokemon1

class PokemonAdapterTypesDetail(private var list: List<DetalhePokemon1>,
                                val context: Context,
                                private var listenner: PokemonInterface? = null
) : RecyclerView.Adapter<PokemonAdapterTypesDetail.ListPokemonViewHolder>() {

    fun updateList(newList: List<DetalhePokemon1>) {
        list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListPokemonViewHolder {
        return ListPokemonViewHolder(
            ListPokemonAdapterTypeDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListPokemonViewHolder, position: Int) {
        val pokemon = list[position]
        val namePokemon = pokemon.nome
        val pokemonId = pokemon.id.toString()

        val pokemon_name = namePokemon.replaceFirstChar { it.titlecase() }
        val pokemon_number = pokemonId.padStart(3, '0')

        if(pokemonId.toInt() < 1099) {
            holder.textViewNumeroAndName.text = "#$pokemon_number $pokemon_name"
        } else {
            holder.textViewNumeroAndName.text = pokemon_name
        }

        holder.constraintLayout.setOnClickListener {
            listenner?.onClick(pokemonId)
        }

        holder.imgViewPokemon.load(pokemon.esprites.other?.home?.front_default)

        holder.recyclerView.layoutManager = GridLayoutManager(
            context.applicationContext,
            pokemon.tipos.size
        )
        holder.recyclerView.adapter = ListTypeAdapter(pokemon.tipos)
    }

    class ListPokemonViewHolder(binding: ListPokemonAdapterTypeDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var textViewNumeroAndName = binding.textView
        var imgViewPokemon = binding.imageView
        var constraintLayout = binding.firstConstraint
        var recyclerView = binding.recyclerView
    }

    override fun getItemCount(): Int = list.size
}
