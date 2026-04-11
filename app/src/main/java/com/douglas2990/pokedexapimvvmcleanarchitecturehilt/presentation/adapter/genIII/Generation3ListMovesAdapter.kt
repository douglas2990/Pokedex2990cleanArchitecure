package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.genIII

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.ItemMoveGen3Binding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.gen_iii.MoveGenIII
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.gen_iii.MoveGenIIIDetail

class Generation3ListMovesAdapter(
    private var moves: List<MoveGenIIIDetail> // Alterado para o objeto detalhado
) : RecyclerView.Adapter<Generation3ListMovesAdapter.MoveViewHolder>() {


    fun updateList(newMoves: List<MoveGenIIIDetail>) {
        val diffCallback = object : DiffUtil.Callback() {
            override fun getOldListSize() = moves.size
            override fun getNewListSize() = newMoves.size
            override fun areItemsTheSame(oldPos: Int, newPos: Int) =
                moves[oldPos].name == newMoves[newPos].name

            override fun areContentsTheSame(oldPos: Int, newPos: Int) =
                moves[oldPos] == newMoves[newPos]
        }
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.moves = newMoves
        diffResult.dispatchUpdatesTo(this)
    }

    class MoveViewHolder(val binding: ItemMoveGen3Binding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoveViewHolder {
        val binding = ItemMoveGen3Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoveViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoveViewHolder, position: Int) {
        val move = moves[position]

        holder.binding.txtMoveName.text = move.name.uppercase().replace("-", " ")
        holder.binding.txtMoveLevel.text = if (move.levelLearnedAt > 0)
            move.levelLearnedAt.toString().padStart(2, '0') else "--"

        // Atualização do Tipo
        if (move.type.isNotEmpty()) {
            holder.binding.txtMoveType.text = move.type.uppercase()
            holder.binding.txtMoveType.visibility = View.VISIBLE
            // Lógica de cores...
            val color = getPokemonTypeColor(move.type)
            holder.binding.txtMoveType.backgroundTintList = ColorStateList.valueOf(Color.parseColor(color))
        } else {
            // Enquanto o tipo não chega da API/Room, mostramos um estado de carregamento ou ocultamos
            holder.binding.txtMoveType.visibility = View.INVISIBLE
        }
    }

    override fun getItemCount(): Int = moves.size

    private fun getPokemonTypeColor(type: String): String {
        return when (type) {
            "fire" -> "#F08030"
            "water" -> "#6890F0"
            "grass" -> "#78C850"
            "electric" -> "#F8D030"
            "ice" -> "#98D8D8"
            "fighting" -> "#C03028"
            "poison" -> "#A040A0"
            "ground" -> "#E0C068"
            "flying" -> "#A890F0"
            "psychic" -> "#F85888"
            "bug" -> "#A8B820"
            "rock" -> "#B8A038"
            "ghost" -> "#705898"
            "dragon" -> "#7038F8"
            "dark" -> "#705848"
            "steel" -> "#B8B8D0"
            else -> "#A8A878" // Normal
        }
    }
}
