package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.ItemMoveGen3Binding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.gen_iii.MoveGenIII

class Generation3ListMovesAdapter(
    private val moves: List<MoveGenIII>
) : RecyclerView.Adapter<Generation3ListMovesAdapter.MoveViewHolder>() {

    class MoveViewHolder(val binding: ItemMoveGen3Binding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoveViewHolder {
        val binding = ItemMoveGen3Binding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MoveViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoveViewHolder, position: Int) {
        val move = moves[position]
        holder.binding.txtMoveName.text = move.name.uppercase().replace("-", " ")
    }

    override fun getItemCount(): Int = moves.size
}
