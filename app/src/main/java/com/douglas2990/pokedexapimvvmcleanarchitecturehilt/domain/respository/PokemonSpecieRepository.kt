package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository

import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.pokemonEspecies.PokemonEspecies

interface PokemonSpecieRepository {

    suspend fun pokemonSpecie(pokemonId: String): PokemonEspecies?
}