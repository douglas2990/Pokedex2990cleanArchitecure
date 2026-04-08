package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.ItemMoveBinding

class MovesGen3StringAdapter(
    private val moves: List<String>
) : RecyclerView.Adapter<MovesGen3StringAdapter.MoveViewHolder>() {

    class MoveViewHolder(val binding: ItemMoveBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoveViewHolder {
        val binding = ItemMoveBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MoveViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoveViewHolder, position: Int) {
        val moveName = moves[position]
        holder.binding.txtMoveName.text = moveName.uppercase().replace("-", " ")
    }

    override fun getItemCount(): Int = moves.size
}
