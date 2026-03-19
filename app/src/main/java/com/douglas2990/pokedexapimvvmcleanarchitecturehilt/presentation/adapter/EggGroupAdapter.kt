package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.pokemonSpecies.EggGroup
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.EggGroupAdapterBinding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.ListTypeAdapterBinding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.pokemonEspecies.GrupoOvos

//class EggGroupAdapter (private val list: List<GrupoOvos>,
class EggGroupAdapter (private val list: List<EggGroup?>,
                       private var listenner: PokemonInterface? = null
)
    : RecyclerView.Adapter<EggGroupAdapter.EggGroupViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EggGroupViewHolder {

        return EggGroupViewHolder(
            EggGroupAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: EggGroupViewHolder, position: Int) {

        val group = list[position]
        holder.buttonType.text = group?.name


    }

    class EggGroupViewHolder(binding: EggGroupAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var buttonType = binding.btnGroup


    }

    override fun getItemCount(): Int {
        return list.size
    }







}