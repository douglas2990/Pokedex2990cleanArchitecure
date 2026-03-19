package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.PokemonAdapterBinding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.detalhe.DetalhePokemon1

class ListPokemonDetailAdapter (private val list: List<DetalhePokemon1>,
                                private var listenner: PokemonInterface? = null
)
    : RecyclerView.Adapter<ListPokemonDetailAdapter.ListPokemonViewHolder>() {

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
        //val namePokemon = pokemon.name
        val namePokemon = pokemon.nome
        //val pokemonId = pokemon.url.replace("https://pokeapi.co/api/v2/pokemon/", "")
        val pokemonId = pokemon.id.toString()


        holder.textViewNamePokemon.text = namePokemon.replaceFirstChar(Char::titlecaseChar)
        if(pokemonId.toInt() < 900) {
            holder.textViewNumero.text = pokemonId.padStart(3, '0')
        }else{
            holder.textViewNumero.text = ""
        }

        holder.textViewNamePokemon.setOnClickListener {
            listenner?.onClick(pokemonId)
        }

        /*
        holder.itemView.setOnClickListener {
            onItemClick?.let {
                it(pokemonId)
            }
        }
         */

        Glide.with(holder.imgViewPokemon)
            .load(pokemon.esprites.other.home.front_default)
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