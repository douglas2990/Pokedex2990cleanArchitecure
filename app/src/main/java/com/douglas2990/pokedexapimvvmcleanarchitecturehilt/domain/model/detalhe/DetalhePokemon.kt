package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.detalhe

import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon.Ability
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon.Cries
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon.Form
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon.GameIndice
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon.Move
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon.Species
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon.Sprites
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon.Stat
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon.Type

data class DetalhePokemon (
    val habilidades: List<Ability>,
    val base_experiencia: Int,
    val cria: Cries,
    val forma: List<Form>,
    val game_indices: List<GameIndice>,
    val altura: Int,
    val held_itens: List<Any>,
    val id: Int,
    val is_defaulque: Boolean,
    val local_area_encontro: String,
    val golpes: List<Move>,
    val nome: String,
    val ordem: Int,
    val past_habilidades: List<Any>,
    val past_tipo: List<Any>,
    val especies: Species,
    val esprites: Sprites,
    val status: List<Stat>,
    val tipos: List<Type>,
    val peso: Int
)