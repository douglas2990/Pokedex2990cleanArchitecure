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

class PokemonAdapterTypesDetail(private val list: List<DetalhePokemon1>,
                                val context: Context,
                                //val adapter: ListTypeAdapter,
                                //val listType: List<Type>,
                                private var listenner: PokemonInterface? = null
)
    : RecyclerView.Adapter<PokemonAdapterTypesDetail.ListPokemonViewHolder>() {

    var gridLayoutManager: GridLayoutManager? = null

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


        val pokemon_name = namePokemon.replaceFirstChar(Char::titlecaseChar)
        val pokemon_number = pokemonId.padStart(3, '0')

        //holder.textViewNumeroAndName.text = namePokemon.replaceFirstChar(Char::titlecaseChar)
        if(pokemonId.toInt() < 1099) {

            holder.textViewNumeroAndName.text = "#$pokemon_number $pokemon_name"
        }else{
            holder.textViewNumeroAndName.text = pokemon_name
        }

        holder.constraintLayout.setOnClickListener {
            listenner?.onClick(pokemonId)
        }


        holder.imgViewPokemon.load(pokemon.esprites.other.home.front_default)


        holder.recyclerView.layoutManager = LinearLayoutManager(context.applicationContext)


        gridLayoutManager = GridLayoutManager(
            context.applicationContext,
            //listType.size
            pokemon.tipos.size
        )
        holder.recyclerView.layoutManager = gridLayoutManager
        holder.recyclerView.adapter = ListTypeAdapter(pokemon.tipos)

        /*
        Glide.with(holder.imgViewPokemon)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + pokemonId + ".png")
            .into(holder.imgViewPokemon)

         */

    }

    class ListPokemonViewHolder(binding: ListPokemonAdapterTypeDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var textViewNumeroAndName = binding.textView
        var imgViewPokemon = binding.imageView
        var constraintLayout = binding.firstConstraint
        var recyclerView = binding.recyclerView

    }

    override fun getItemCount(): Int {
        return list.size
    }







}