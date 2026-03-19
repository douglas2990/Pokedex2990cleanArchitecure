package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository

import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.detalhe.DetalhePokemon


interface DetalhePokemonRepository {
    //val pokemonId: String

    suspend fun detalhePokemon(pokemonId: String) : DetalhePokemon?
    //suspend fun detalhePokemon() : DetalhePokemon?
}

