package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.detalhe

import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon.Sprites
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon.Stat
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon.Type

data class DetalhePokemonPython(
    val id: Int,
    val nome: String,
    val esprites: Sprites,
    val tipos: List<Type>,
    val status: List<Stat>
)
