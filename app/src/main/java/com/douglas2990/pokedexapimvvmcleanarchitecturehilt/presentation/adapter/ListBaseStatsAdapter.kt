package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.green
import androidx.core.graphics.toColor
import androidx.recyclerview.widget.RecyclerView
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.R
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon.Stat
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon.Type
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.BaseStatusAdapterBinding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.ListTypeAdapterBinding


class ListBaseStatsAdapter (private val list: List<Stat>,
                            private var listenner: PokemonInterface? = null
)
    : RecyclerView.Adapter<ListBaseStatsAdapter.ListBaseStatsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListBaseStatsViewHolder {

        return ListBaseStatsViewHolder(
            BaseStatusAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun onBindViewHolder(holder: ListBaseStatsViewHolder, position: Int) {

        val base_status = list[position]
        holder.statusBaseName.text = base_status.stat.name
        holder.statusProgress.progress = base_status.base_stat
        holder.statusProgress.progressTintList = ColorStateList.valueOf(R.color.my_dark_primary)
        holder.statusValorBase.text = base_status.base_stat.toString()


    }

    class ListBaseStatsViewHolder(binding: BaseStatusAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
            var statusBaseName = binding.textNameBaseStatus
            var statusProgress = binding.progressBarStatus
            var statusValorBase = binding.textNameValorStatus


    }

    override fun getItemCount(): Int {
        return list.size
    }







}