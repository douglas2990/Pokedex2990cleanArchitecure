package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon.Stat
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.ItemBaseStatsSixthBinding

class SixthBaseStatsAdapter(private val statsList: List<Stat>) :
    RecyclerView.Adapter<SixthBaseStatsAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemBaseStatsSixthBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBaseStatsSixthBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val stat = statsList[position]
        val statName = stat.stat.name.lowercase()
        
        holder.binding.textStatName.text = stat.stat.name.uppercase()
        holder.binding.textStatValue.text = stat.base_stat.toString()
        holder.binding.progressBarStat.progress = stat.base_stat

        // Definindo as cores baseadas no tipo de stat, igual ao FourthFragment
        val color = when (statName) {
            "hp" -> "#FF0000"
            "attack" -> "#F08030"
            "defense" -> "#F8D030"
            "special-attack" -> "#6890F0"
            "special-defense" -> "#78C850"
            "speed" -> "#F85888"
            else -> "#009688"
        }
        
        holder.binding.progressBarStat.progressTintList = ColorStateList.valueOf(Color.parseColor(color))
    }

    override fun getItemCount(): Int = statsList.size
}
