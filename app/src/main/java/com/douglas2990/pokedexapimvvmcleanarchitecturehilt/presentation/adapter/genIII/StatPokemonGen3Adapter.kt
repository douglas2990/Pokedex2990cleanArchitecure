package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.genIII

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.BaseStatusAdapterBinding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.gen_iii.PokemonStatGen3

class StatPokemonGen3Adapter(
    private val stats: List<PokemonStatGen3>
) : RecyclerView.Adapter<StatPokemonGen3Adapter.StatViewHolder>() {

    class StatViewHolder(val binding: BaseStatusAdapterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatViewHolder {
        val binding = BaseStatusAdapterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return StatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StatViewHolder, position: Int) {
        val stat = stats[position]
        holder.binding.textNameBaseStatus.text = stat.name.uppercase()
        holder.binding.textNameValorStatus.text = stat.baseStat.toString()

        holder.binding.progressBarStatus.progress = stat.baseStat

        // Cor dinâmica baseada no valor
        val color = when {
            stat.baseStat < 50 -> "#FF5959"  // Ruim
            stat.baseStat < 90 -> "#FFCC52"  // Médio
            else -> "#48D0B0"                // Bom
        }
        holder.binding.progressBarStatus.progressTintList = ColorStateList.valueOf(Color.parseColor(color))
    }

    override fun getItemCount(): Int = stats.size
}
