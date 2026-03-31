package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon.Move
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon2.MoveSlotDTO
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.ItemMoveBinding

class PokemonMovesAdapter(private val moves: List<Move>) :
    RecyclerView.Adapter<PokemonMovesAdapter.MoveViewHolder>() {

    class MoveViewHolder(val binding: ItemMoveBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoveViewHolder {
        val binding = ItemMoveBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoveViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoveViewHolder, position: Int) {
        val moveSlot = moves[position]
        // Formata o nome: remove hifens e coloca em maiúsculo
        holder.binding.txtMoveName.text = moveSlot.move.name.replace("-", " ")
    }

    override fun getItemCount() = moves.size
}