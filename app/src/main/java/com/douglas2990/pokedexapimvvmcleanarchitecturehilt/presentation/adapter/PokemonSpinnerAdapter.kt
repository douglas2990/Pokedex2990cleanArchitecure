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


class PokemonSpinnerAdapter(context: Context,list: List<DetalhePokemon1>,
                            private var listenner: PokemonInterface? = null
) :
    ArrayAdapter<DetalhePokemon1>(context,0, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    private fun initView(position: Int, convertView: View?, parent: ViewGroup): View{

        val pokemonList = getItem(position)

        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.pokemon_adapter,parent,false)
        val texViewPokemon: TextView = view.findViewById(R.id.txtNomePokemon)
        val textViewNumero: TextView = view.findViewById(R.id.txtNumeroPokemon)
        val imgViewPokemon: ImageView = view.findViewById(R.id.imgPokemon)

        texViewPokemon.text = pokemonList?.nome?.replaceFirstChar(Char::titlecaseChar)

        val pokemonId = pokemonList?.id.toString()

        if(pokemonId !!.toInt() < 900) {
            //textViewNumero.text = pokemonId.padStart(3, '0')
            textViewNumero.text = pokemonId.toString()
        }else{
            textViewNumero.text = ""
        }

        Glide.with(imgViewPokemon)
            .load(pokemonList?.esprites?.other?.home?.front_default)
            .into(imgViewPokemon)


        convertView?.setOnClickListener {
            listenner?.click(pokemonId.toString())
        }

        return view
    }

    interface PokemonInterface {
        fun click(pokemonId: String)
    }


}