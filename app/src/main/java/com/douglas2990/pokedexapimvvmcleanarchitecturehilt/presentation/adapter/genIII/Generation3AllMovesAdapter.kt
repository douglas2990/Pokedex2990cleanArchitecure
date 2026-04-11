package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.genIII

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.ItemMoveGen3Binding // Reutilizando o layout ou use um mais simples
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.gen_iii.MoveGenIII

class Generation3AllMovesAdapter :
    ListAdapter<MoveGenIII, Generation3AllMovesAdapter.ViewHolder>(MoveDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMoveGen3Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val move = getItem(position)
        holder.binding.txtMoveName.text = move.name.replace("-", " ").uppercase()

        // Como é uma lista geral, podemos esconder ou colocar um texto padrão nos campos de detalhe
        holder.binding.txtMoveType.text = "???"
        holder.binding.txtMoveLevel.text = "--"
    }

    class ViewHolder(val binding: ItemMoveGen3Binding) : RecyclerView.ViewHolder(binding.root)

    class MoveDiffCallback : DiffUtil.ItemCallback<MoveGenIII>() {
        override fun areItemsTheSame(oldItem: MoveGenIII, newItem: MoveGenIII) = oldItem.name == newItem.name
        override fun areContentsTheSame(oldItem: MoveGenIII, newItem: MoveGenIII) = oldItem == newItem
    }
}