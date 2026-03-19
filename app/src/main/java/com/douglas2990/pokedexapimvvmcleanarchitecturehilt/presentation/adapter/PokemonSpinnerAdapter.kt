package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.R
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.detalhe.DetalhePokemon1

class PokemonSpinnerAdapter(
    context: Context,
    private val list: List<DetalhePokemon1>
) : ArrayAdapter<DetalhePokemon1>(context, 0, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    private fun initView(position: Int, convertView: View?, parent: ViewGroup): View {
        val pokemon = getItem(position)
        
        // Reutilização de view otimizada
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.pokemon_adapter, parent, false)
        
        val texViewPokemon: TextView = view.findViewById(R.id.txtNomePokemon)
        val textViewNumero: TextView = view.findViewById(R.id.txtNumeroPokemon)
        val imgViewPokemon: ImageView = view.findViewById(R.id.imgPokemon)

        texViewPokemon.text = pokemon?.nome?.replaceFirstChar { it.titlecase() }
        
        val pokemonId = pokemon?.id?.toString() ?: ""
        if (pokemonId.isNotEmpty() && pokemonId.toInt() < 1000) {
            textViewNumero.text = "#${pokemonId.padStart(3, '0')}"
        } else {
            textViewNumero.text = ""
        }

        // Carregamento de imagem eficiente
        Glide.with(view.context)
            .load(pokemon?.esprites?.other?.home?.front_default)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(imgViewPokemon)

        // IMPORTANTE: Removemos o setOnClickListener de dentro do Adapter.
        // Em um Spinner, o clique deve ser tratado pelo onItemSelectedListener do Spinner na Fragment.
        // Se houver um ClickListener aqui, ele "rouba" o toque e o Spinner não seleciona o item.

        return view
    }
}
