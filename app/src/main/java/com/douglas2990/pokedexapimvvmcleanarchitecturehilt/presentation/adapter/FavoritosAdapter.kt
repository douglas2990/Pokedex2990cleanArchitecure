package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.PokemonAdapterBinding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.PokemonFavorito

class FavoritosAdapter(
    private val list: List<PokemonFavorito>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<FavoritosAdapter.FavoritoViewHolder>() {

    class FavoritoViewHolder(val binding: PokemonAdapterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritoViewHolder {
        return FavoritoViewHolder(
            PokemonAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavoritoViewHolder, position: Int) {
        val pokemon = list[position]
        holder.binding.txtNomePokemon.text = pokemon.nome.replaceFirstChar { it.titlecase() }
        holder.binding.txtNumeroPokemon.text = "#${pokemon.id.toString().padStart(3, '0')}"
        holder.binding.imgPokemon.load(pokemon.imageUrl)

        holder.itemView.setOnClickListener {
            onClick(pokemon.id.toString())
        }
    }

    override fun getItemCount(): Int = list.size
}
