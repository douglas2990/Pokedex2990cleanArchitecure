package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.genIII

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.ItemPokemonGen3GridBinding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.gen_iii.PokemonSpecies

class PokemonSpeciesGenIIIAdapter(
    private val list: List<PokemonSpecies>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<PokemonSpeciesGenIIIAdapter.SpeciesViewHolder>() {

    class SpeciesViewHolder(val binding: ItemPokemonGen3GridBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesViewHolder {
        val binding = ItemPokemonGen3GridBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SpeciesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SpeciesViewHolder, position: Int) {
        val species = list[position]
        val pokemonId = extractId(species.url)
        
        holder.binding.txtNomeGrid.text = species.name.replaceFirstChar { it.titlecase() }
        holder.binding.txtNumeroGrid.text = "#${pokemonId.padStart(3, '0')}"
        
        // Usando as imagens clássicas de Ruby/Sapphire conforme solicitado
        val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/ruby-sapphire/$pokemonId.png"
        holder.binding.imgPokemonGrid.load(imageUrl)

        holder.itemView.setOnClickListener {
            onClick(pokemonId)
        }
    }

    override fun getItemCount(): Int = list.size

    private fun extractId(url: String): String {
        return url.split("/").filter { it.isNotEmpty() }.last()
    }
}
