package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.PokemonAdapterBinding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.Resultado

class ListPokemonAdapter(
    private var list: List<Resultado>,
    private var listenner: PokemonInterface? = null
) : RecyclerView.Adapter<ListPokemonAdapter.ListPokemonViewHolder>() {

    fun updateList(newList: List<Resultado>) {
        list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListPokemonViewHolder {
        return ListPokemonViewHolder(
            PokemonAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListPokemonViewHolder, position: Int) {
        val pokemon = list[position]
        val namePokemon = pokemon.nome
        val pokemonId = pokemon.urlDaApi.replace("https://pokeapi.co/api/v2/pokemon/", "")
            .replace("/", "")

        holder.textViewNamePokemon.text = namePokemon.replaceFirstChar { it.titlecase() }
        
        if (pokemonId.isNotEmpty() && pokemonId.all { it.isDigit() }) {
            holder.textViewNumero.text = pokemonId.padStart(3, '0')
        } else {
            holder.textViewNumero.text = ""
        }

        holder.itemView.setOnClickListener {
            listenner?.onClick(pokemonId)
        }

        Glide.with(holder.imgViewPokemon)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$pokemonId.png")
            .into(holder.imgViewPokemon)
    }

    class ListPokemonViewHolder(binding: PokemonAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var textViewNumero = binding.txtNumeroPokemon
        var textViewNamePokemon = binding.txtNomePokemon
        val imgViewPokemon = binding.imgPokemon
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
